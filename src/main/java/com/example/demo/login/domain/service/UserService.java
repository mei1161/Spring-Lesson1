package com.example.demo.login.domain.service;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.example.demo.login.domain.User;
import com.example.demo.login.domain.repository.UserDao;
@Transactional
@Service
public class UserService {
	
	@Autowired
	UserDao dao;
	
	//Point: 明示的トランザクション
	@Autowired
	PlatformTransactionManager txManager;
	
	
	//insert用メソッド
	public boolean insert(User user) {
		
		//insert実行
		int rowNumber = dao.insertOne(user);
		
		//判定用変数
		boolean result = false;
		
		if(rowNumber > 0) {
			//insert成功
			result = true;
		}
		return result;
	}
	//カウント用メソッド
	public int count() {
		return dao.count();
	}
	
	//全件取得用メソッド
	public List<User> selectMany(){
		//全件取得
		return dao.selectMany();
	}
	
	//1件取得用メソッド
	public User selectOne(String userId) {
		//selectOne実行
		return dao.selectOne(userId);
	}
	
	//1件更新メソッド
	public boolean updateOne(User user) throws DataAccessException {
		
		//インスタンス生成
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		
		//設定
		def.setName("UpdateUser");
		def.setReadOnly(false);
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		//トランザクション開始
		TransactionStatus status = txManager.getTransaction(def);
		
		//判定用変数
		boolean result = false;
		
		try {
			//1件更新
			int rowNumber = dao.updateOne(user);
			
			if(rowNumber > 0) {
				//update成功
				result = true;
			}
		}catch(Exception e) {
			//ロールバック
			txManager.rollback(status);
			throw new DataAccessException("ERROR Update",e) {};
		}
		//コミット
		txManager.commit(status);
		
		return result;
	}
	
	//1件削除メソッド
	public boolean deleteOne(String userId) {
		//1件削除
		int rowNumber = dao.deleteOne(userId);
		
		//判定用変数
		boolean result = false;
		
		if(rowNumber > 0) {
			//delete成功
			result = true;
		}
		return result;
	}
	
	//ユーザー一覧をCSV出力
	public void userCsvOut() throws DataAccessException{
		//CSV出力
		dao.userCsvOut();
	}
	
	//サーバーに保存されているファイルを取得して、byte配列に変換する
	public byte[] getFile(String fileName)throws IOException{
		
		//ファイルシステム(デフォルト)の取得
		FileSystem fs = FileSystems.getDefault();
		
		//ファイル取得
		Path p = fs.getPath(fileName);
		
		//ファイルをbyte配列に変換
		byte[] bytes = Files.readAllBytes(p);
		
		return bytes;
	}
}

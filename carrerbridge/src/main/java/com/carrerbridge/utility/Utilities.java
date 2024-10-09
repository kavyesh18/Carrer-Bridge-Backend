package com.carrerbridge.utility;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.carrerbridge.entity.Sequence;
import com.carrerbridge.exception.CarrerBridgeException;

@Component
public class Utilities {
	
	private static MongoOperations mongoOperation;
	
	@Autowired
	public void setMongoOperation(MongoOperations mongoOperation) {
		Utilities.mongoOperation = mongoOperation;
	}
	
	public static Long getNextSequence (String key) throws CarrerBridgeException {
		Query query = new Query(Criteria.where("_id").is(key));
		Update update = new Update();
		update.inc("seq",1);
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		Sequence seq = mongoOperation.findAndModify(query, update, options, Sequence.class);
		if(seq==null)throw new CarrerBridgeException("Unable to Find Sequence id for key:"+key);
		return seq.getSeq();
	}
	
	public static String generateOtp() {
		StringBuilder otp = new StringBuilder();
		SecureRandom random = new SecureRandom();
		for(int i=0;i<6;i++)otp.append(random.nextInt(10));
		return otp.toString();
	}
}

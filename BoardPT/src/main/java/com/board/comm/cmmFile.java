package com.board.comm;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class cmmFile {
	
	private static Logger log = LoggerFactory.getLogger(cmmFile.class);
	
	private static String file_path;
	
	@Value("${file.path}")
	public void setFile_Path(String path) {
		file_path = path;
	}
	
	
	public static String fileUpload(MultipartFile file) {	// 저장된 파일 이름을 리턴
		log.info("fileUpload!");
		
		String file_name	= file.getOriginalFilename();
		String extra		= "__";
		
		File f = new File(file_path);
		if (f.exists()) {
			System.out.println("경로 존재함");
		}else {
			System.out.println("존재하지 않는 경로");
			
			if(f.mkdirs()) {
				log.info("경로 생성 완료 :: {}", file_path);
			}else {
				log.info("경로 생성 실패 :: {}", file_path);
				return "";
			}
		}
		
		String[] split_name	= file_name.split("\\.");
		DateFormat df 		= new SimpleDateFormat("yyyyMMddHmsS");
		String dateStr		= df.format(new Date());
		file_name 			= split_name[0] + extra + dateStr + "." +split_name[1];
		// svnm : olnm[0] + "__yyyyMMddHmsS" + "." + olnm[1]

		int i = 0;
		while (true) {
			f = new File(file_path + file_name);
			
			if (f.exists()) {
				log.info("파일이 이미 존재함 :: {}", file_name);
				file_name = split_name[0] + "_" + ++i + split_name[1];
				
			} else {
				log.info("해당 이름 파일 존재하지 않음");
				break;
			}
			
		}
		
		Path copyPath = Paths.get(file_path + StringUtils.cleanPath(file_name));
		log.info("@#EWQWEWE");
		try {
			Files.copy(file.getInputStream(), copyPath, StandardCopyOption.REPLACE_EXISTING);
			log.info("파일 생성 완료! :: {}", file_name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return file_name;
	}
	
	
	public static byte[] fileDownload(String file_svnm) throws Exception {
		
		File file		= new File(file_path + file_svnm);
		byte fileByte[] = FileUtils.readFileToByteArray(file);
		
		return fileByte;
	}

}

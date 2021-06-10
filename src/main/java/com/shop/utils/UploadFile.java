package com.shop.utils;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFile {
	public File handUploadFile(MultipartFile uploadFile
			) {
			String folderPath ="D:\\Users\\eclipse-workspace\\Shop\\src\\main\\webapp\\storage";
			File myUploadFolder = new File(folderPath);
			// kiểm tra thư mục lưu trữ file có tồn tại ? nếu không thì tạo thư mục mới
			if(!myUploadFolder.exists()) {
				myUploadFolder.mkdirs();
			}
			File sevedFile =null;
			try {
				// lưu file vào thư mục đã chọn
				String uuid = UUID.randomUUID().toString();
				String fileName = uuid + "_" +uploadFile.getOriginalFilename();
				sevedFile = new File(myUploadFolder,fileName);
				uploadFile.transferTo(sevedFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		  return sevedFile;
		}
}

package com.sinapsisenergia.fileencryptor;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;

public final class FileEncryptor {

	private File file;
	
	public FileEncryptor(String filePath) throws IOException {
		file = new File(filePath);
		
		if (!file.exists() || file.isDirectory() || !file.canRead() || !file.canWrite()) {
			throw new IOException();
		}
		
	}
	
	public FileEncryptor(Path path) throws IOException {
		this(path.toString());
	}
	
	public FileEncryptor(File file) throws IOException {
		this(file.getCanonicalPath());
	}
	
	public void encrypt() {
		
		byte[] initialBytes = new byte[100];
		byte[] middleBytes = new byte[100];
		
		try (RandomAccessFile raf = new RandomAccessFile(file, "rw");) {
			
			//
			// reading first 100 bytes
			//
			raf.read(initialBytes);
			
			raf.seek(0);
			long middlePosition = raf.length() / 2;
			
			//
			// setting file-pointer offset to the middle of the file  
			//
			raf.seek(middlePosition);
			
			//
			// reading 100 bytes starting on the middle of the file
			//
			raf.read(middleBytes);
			
			//
			// writting the 100 middle bytes at the beginning of the file 
			//
			raf.seek(0);
			raf.write(middleBytes);
			
			//
			// writting the 100 initial bytes at the middle of the file
			//
			raf.seek(middlePosition);
			raf.write(initialBytes);
			
			raf.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void decrypt() {
		
		encrypt();
		
	}
	
}

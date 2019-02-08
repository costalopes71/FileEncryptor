package com.sinapsisenergia.fileencryptor.example;

import java.io.File;
import java.io.IOException;

import com.sinapsisenergia.fileencryptor.FileEncryptor;

public class UsageExample {

	public static void main(String[] args) {
		
		
		File f = new File("/home/joao/Documentos/workspaces/FileEncryptor_workspace/docs/CEB_TODAS_SUB_20190207-110821_855.cbdb");
		
		FileEncryptor enc;
		try {
			enc = new FileEncryptor(f);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		enc.decrypt();
		
	}
	
}

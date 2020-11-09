package com.scaffold.easy.jenkins.server.utils;

import java.io.*;

public class InputStreamRunnable implements Runnable {

	BufferedReader bReader = null;

	public InputStreamRunnable(InputStream is) throws UnsupportedEncodingException {
		bReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(is), "utf-8"));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String line;
		try {
			while ((line = bReader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}

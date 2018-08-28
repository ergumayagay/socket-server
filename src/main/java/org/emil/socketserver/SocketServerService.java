package org.emil.socketserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;

@Component
public class SocketServerService {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(SocketServerService.class);

	private SocketServerProperties socketProperties;

	private ServerSocket serverSocket;

	private Socket clientSocket;

	private PrintWriter out;
	
	private BufferedReader in;

	public void start(int port) throws IOException {

		// Init server socket
		serverSocket = new ServerSocket(port);
		logger.info("Server socket created at port: {} ", serverSocket.getLocalPort());

		clientSocket = serverSocket.accept();

		// Print from client socket
		out = new PrintWriter(clientSocket.getOutputStream());

		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		String response = in.readLine();

		logger.info("response: {}", response);
	}

	public void stop() {
		try {
			in.close();
			out.close();
			clientSocket.close();
			serverSocket.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

	}
}

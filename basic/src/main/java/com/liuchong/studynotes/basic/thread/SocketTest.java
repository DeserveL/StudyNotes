/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liuchong.studynotes.basic.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author DeserveL
 * @date 2017/5/17 9:57
 * @since 1.0.0
 */
public class SocketTest {
    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(8090);
        try{
            while(true){
                Socket socket = listener.accept();
                System.out.println(socket.getInetAddress());
            }
        }finally {
            listener.close();
        }
    }

    public void HandleRequest(Socket socket){

    }
}

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
package com.deservel.designpatterns.decorator.demo;

import java.io.*;

/**
 * IO包中的部分装饰过程
 *
 * @author DeserveL
 * @date 2017/6/23 11:54
 * @since 1.0.0
 */
public class IOTest {

    public static void main(String[] args) throws IOException {
        //测试文件
        final String filePath = IOTest.class.getResource("/").getPath() + "designpatterns/decoratorTest.txt";

        //InputStream相当于被装饰的接口或者抽象类，FileInputStream相当于原始的待装饰的对象，FileInputStream无法装饰InputStream
        //另外FileInputStream是以只读方式打开了一个文件,并打开了一个文件的句柄存放在FileDescriptor对象的handle属性
        //所以下面有关回退和重新标记等操作，都是在堆中建立缓冲区所造成的假象,并不是真正的文件流在回退或者重新标记
        InputStream inputStream = new FileInputStream(filePath);
        final int available = inputStream.available(); //记录流的长度
        System.out.println("FileInputStream不支持mark和reset：" + inputStream.markSupported());

        System.out.println("------------------------我是一个优雅的分隔符-------------------");

        /* 下面分别展示三种装饰器的作用BufferedInputStream,DataInputStream,PushbackInputStream  */

        //首先装饰成BufferedInputStream，它提供我们mark，reset的功能
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        System.out.println("BufferedInputStream支持mark和reset：" + bufferedInputStream.markSupported());
        bufferedInputStream.mark(1); //标记
        char c = (char) bufferedInputStream.read();
        System.out.println("文件的第一个字符：" + c);
        bufferedInputStream.mark(0); //标记,mark(int readlimit)方法表示，标记当前位置，并保证在mark以后最多可以读取readlimit字节数据，mark标记仍有效..事实上，mark在JAVA中的实现是和缓冲区相关的。只要缓冲区够大，mark后读取的数据没有超出缓冲区的大小，mark标记就不会失效。
        c = (char) bufferedInputStream.read(); //再读
        System.out.println("再读一个字符：" + c);
        bufferedInputStream.reset(); //重置
        c = (char) bufferedInputStream.read(); //再读
        System.out.println("重置以后再读一个字符，依然会是第一个字符:" + c);

        System.out.println("------------------------我是一个优雅的分隔符-------------------");

        //装饰成 DataInputStream,我们为了又使用DataInputStream,又使用BufferedInputStream的mark reset功能，所以我们再进行一层包装
        //注意，这里如果不使用BufferedInputStream，而使用原始的InputStream，read方法返回的结果会是-1，即已经读取结束
        //因为BufferedInputStream已经将文本的内容读取完毕，并缓冲到堆上，默认的初始缓冲区大小是8192B
        DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);
        dataInputStream.reset();//这是BufferedInputStream提供的功能，如果不在这个基础上包装会出错
        System.out.println("DataInputStream现在具有readInt，readChar,readUTF等功能");
        int value = dataInputStream.readInt();//读出来一个int,包含四个字节
        //我们转换成字符依次显示出来，可以看到LZ文件的前四个字符
        String binary = Integer.toBinaryString(value);
        int first = binary.length() % 8;
        System.out.print("使用readInt读取的前四个字符：");
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                System.out.print(((char) Integer.valueOf(binary.substring(0, first), 2).intValue()));
            } else {
                System.out.print(((char) Integer.valueOf(binary.substring((i - 1) * 8 + first, i * 8 + first), 2).intValue()));
            }
        }
        System.out.println();

        System.out.println("------------------------我是一个优雅的分隔符-------------------");

        //PushbackInputStream无法包装BufferedInputStream支持mark reset，因为它覆盖了reset和mark方法
        //因为流已经被读取到末尾，所以我们必须重新打开一个文件的句柄，即FileInputStream
        inputStream = new FileInputStream(filePath);
        PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream, available);//装饰成 PushbackInputStream
        System.out.println("PushbackInputStream装饰以后支持退回操作unread");
        byte[] bytes = new byte[available];
        pushbackInputStream.read(bytes);//读完了整个流
        System.out.println("unread回退前的内容：" + new String(bytes));
        pushbackInputStream.unread(bytes);//再退回去
        bytes = new byte[available];//清空byte数组
        pushbackInputStream.read(bytes);//再读
        System.out.println("unread回退后的内容：" + new String(bytes));

        System.out.println("------------------------我是一个优雅的分隔符-------------------");

        /*  以上有两个一层装饰和一个两层装饰,下面我们先装饰成Reader，再进行其它装饰   */

        //由于之前被PushbackInputStream将流读取到末尾，我们需要再次重新打开文件句柄
        inputStream = new FileInputStream(filePath);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");//先装饰成InputStreamReader
        System.out.println("InputStreamReader有reader的功能，比如转码：" + inputStreamReader.getEncoding());

        System.out.println("------------------------我是一个优雅的分隔符-------------------");

        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);//我们进一步在reader的基础上装饰成BufferedReader
        System.out.println("BufferedReader有readLine等功能：" + bufferedReader.readLine());

        System.out.println("------------------------我是一个优雅的分隔符-------------------");

        LineNumberReader lineNumberReader = new LineNumberReader(inputStreamReader);//我们进一步在reader的基础上装饰成LineNumberReader
        System.out.println("LineNumberReader有设置行号，获取行号等功能（行号从0开始）,当前行号：" + lineNumberReader.getLineNumber());

        System.out.println("------------------------我是一个优雅的分隔符-------------------");

        //此处由于刚才被readLine方法将流读取到末尾,所以我们再次重新打开文件句柄,并需要将inputstream再次包装成reader
        inputStreamReader = new InputStreamReader(new FileInputStream(filePath));
        PushbackReader pushbackReader = new PushbackReader(inputStreamReader, available);//我们进一步在reader的基础上装饰成PushbackReader
        System.out.println("PushbackReader是拥有退回操作的reader对象");
        char[] chars = new char[available];
        pushbackReader.read(chars);
        System.out.println("unread回退前的内容：" + new String(chars));
        pushbackReader.unread(chars);//再退回去
        chars = new char[available];//清空char数组
        pushbackReader.read(chars);//再读
        System.out.println("unread回退后的内容：" + new String(chars));

    }
}

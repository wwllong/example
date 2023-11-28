package com.wenwl.example.hello.netty.serivce;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author jack.wen
 * @since 2023/10/31 11:30
 */
public class MyServer {

    public static void main(String[] args) throws Exception {
        //创建两个线程组 boosGroup、workerGroup,默认的线程数是cpu核数的两倍
        // bossGroup 用于监听客户端连接，专门负责与客户端创建连接，并把连接注册到workerGroup的Selector中。
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // workerGroup用于处理每一个连接发生的读写事件。
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //创建服务端的启动对象，设置参数
            ServerBootstrap bootstrap = new ServerBootstrap();
            //设置两个线程组boosGroup和workerGroup
            bootstrap.group(bossGroup, workerGroup)
                    //设置服务端通道实现类型, 当建立连接后，会根据这个设置创建对应的Channel实例。
                    //通道类型有以下：
                      //NioSocketChannel： 异步非阻塞的客户端 TCP Socket 连接。
                      //NioServerSocketChannel： 异步非阻塞的服务器端 TCP Socket 连接。
                        //常用的就是这两个通道类型，因为是异步非阻塞的。所以是首选。
                      //OioSocketChannel： 同步阻塞的客户端 TCP Socket 连接。
                      //OioServerSocketChannel： 同步阻塞的服务器端 TCP Socket 连接。
                      //NioSctpChannel： 异步的客户端 Sctp（Stream Control Transmission Protocol，流控制传输协议）连接。
                      //NioSctpServerChannel： 异步的 Sctp 服务器端连接。
                    .channel(NioServerSocketChannel.class)
                    //设置线程队列得到连接个数, 即设置的是服务端用于接收进来的连接，也就是boosGroup线程。
                      // Socket参数，服务端接受连接的队列长度，如果队列已满，客户端连接将被拒绝。默认值，Windows为200，其他为128。
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //设置保持活动连接状态，即提供给父管道接收到的连接，也就是workerGroup线程。
                      // SO_RCVBUF Socket参数，TCP数据接收缓冲区大小。
                      // TCP_NODELAY TCP参数，立即发送数据，默认值为Ture。
                      // SO_KEEPALIVE Socket参数，连接保活，默认值为False。启用该功能时，TCP会主动探测空闲连接的有效性。
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //使用匿名内部类的形式初始化通道对象#重点！！！
                    // ChannelPipeline是Netty处理请求的责任链，ChannelHandler则是具体处理请求的处理器。实际上每一个channel都有一个处理器的流水线。
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        // 重写initChannel()初始化通道的方法，装配流水线就是在这个地方进行
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //给pipeline管道设置处理器
                            socketChannel.pipeline().addLast(new MyServerHandler());
                            // 处理器Handler主要分为两种：ChannelInboundHandlerAdapter(入站处理器)、ChannelOutboundHandler(出站处理器)
                            // 入站指的是数据从底层java NIO Channel到Netty的Channel。
                            // 出站指的是通过Netty的Channel来操作底层的java NIO Channel。
                            //
                            // ChannelInboundHandlerAdapter处理器常用的事件有：
                            //  注册事件 fireChannelRegistered。
                            //  连接建立事件 fireChannelActive。
                            //  读事件和读完成事件 fireChannelRead、fireChannelReadComplete。
                            //  异常通知事件 fireExceptionCaught。
                            //  用户自定义事件 fireUserEventTriggered。
                            //  Channel 可写状态变化事件 fireChannelWritabilityChanged。
                            // 连接关闭事件 fireChannelInactive。
                            //  ChannelOutboundHandler处理器常用的事件有：
                            //  端口绑定 bind。
                            //  连接服务端 connect。
                            //  写事件 write。
                            //  刷新时间 flush。
                            //  读事件 read。
                            //  主动断开连接 disconnect。
                            //  关闭 channel 事件 close。
                        }
                    });//给workerGroup的EventLoop对应的管道设置处理器
            System.out.println("java技术爱好者的服务端已经准备就绪...");
            //绑定端口号，启动服务端
            ChannelFuture channelFuture = bootstrap.bind(6666)
                    // ChannelFuture提供操作完成时一种异步通知的方式。
                    // 一般在Socket编程中，等待响应结果都是同步阻塞的，而Netty则不会造成阻塞，因为ChannelFuture是采取类似观察者模式的形式进行获取结果。请看一段代码演示：
                    .addListener(new ChannelFutureListener() {
                        //使用匿名内部类，ChannelFutureListener接口
                        //重写operationComplete方法
                        @Override
                        public void operationComplete(ChannelFuture future) throws Exception {
                            //判断是否操作成功
                            if (future.isSuccess()) {
                                System.out.println("连接成功");
                            } else {
                                System.out.println("连接失败");
                            }
                        }
                    }).sync();
            // channel为用户提供：
            //  通道当前的状态（例如它是打开？还是已连接？）
            //  channel的配置参数（例如接收缓冲区的大小）
            //  channel支持的IO操作（例如读、写、连接和绑定），以及处理与channel相关联的所有IO事件和请求的ChannelPipeline。

            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

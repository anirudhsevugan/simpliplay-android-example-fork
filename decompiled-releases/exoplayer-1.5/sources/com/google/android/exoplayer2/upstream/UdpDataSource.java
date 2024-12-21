package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

public final class UdpDataSource extends BaseDataSource {
    private final byte[] a;
    private final DatagramPacket b;
    private Uri c;
    private DatagramSocket d;
    private MulticastSocket e;
    private InetAddress f;
    private InetSocketAddress g;
    private boolean h;
    private int i;

    public final class UdpDataSourceException extends IOException {
        public UdpDataSourceException(IOException iOException) {
            super(iOException);
        }
    }

    public UdpDataSource() {
        this((byte) 0);
    }

    private UdpDataSource(byte b2) {
        this(0);
    }

    private UdpDataSource(char c2) {
        super(true);
        byte[] bArr = new byte[2000];
        this.a = bArr;
        this.b = new DatagramPacket(bArr, 0, 2000);
    }

    public final int a(byte[] bArr, int i2, int i3) {
        if (i3 == 0) {
            return 0;
        }
        if (this.i == 0) {
            try {
                this.d.receive(this.b);
                int length = this.b.getLength();
                this.i = length;
                a(length);
            } catch (IOException e2) {
                throw new UdpDataSourceException(e2);
            }
        }
        int length2 = this.b.getLength();
        int i4 = this.i;
        int min = Math.min(i4, i3);
        System.arraycopy(this.a, length2 - i4, bArr, i2, min);
        this.i -= min;
        return min;
    }

    public final long a(DataSpec dataSpec) {
        DatagramSocket datagramSocket;
        Uri uri = dataSpec.a;
        this.c = uri;
        String host = uri.getHost();
        int port = this.c.getPort();
        d();
        try {
            this.f = InetAddress.getByName(host);
            this.g = new InetSocketAddress(this.f, port);
            if (this.f.isMulticastAddress()) {
                MulticastSocket multicastSocket = new MulticastSocket(this.g);
                this.e = multicastSocket;
                multicastSocket.joinGroup(this.f);
                datagramSocket = this.e;
            } else {
                datagramSocket = new DatagramSocket(this.g);
            }
            this.d = datagramSocket;
            try {
                this.d.setSoTimeout(8000);
                this.h = true;
                b(dataSpec);
                return -1;
            } catch (SocketException e2) {
                throw new UdpDataSourceException(e2);
            }
        } catch (IOException e3) {
            throw new UdpDataSourceException(e3);
        }
    }

    public final Uri a() {
        return this.c;
    }

    public final void c() {
        this.c = null;
        MulticastSocket multicastSocket = this.e;
        if (multicastSocket != null) {
            try {
                multicastSocket.leaveGroup(this.f);
            } catch (IOException e2) {
            }
            this.e = null;
        }
        DatagramSocket datagramSocket = this.d;
        if (datagramSocket != null) {
            datagramSocket.close();
            this.d = null;
        }
        this.f = null;
        this.g = null;
        this.i = 0;
        if (this.h) {
            this.h = false;
            e();
        }
    }
}

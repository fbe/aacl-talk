package name.felixbecker.aacl.ownclassloader.classloader;

public class SyntheticGreeterClassLoader extends ClassLoader {

    char[] greeterClassBytes = new char[]{

        0xca, 0xfe, 0xba, 0xbe, // Haha, nerds.
        0x00, 0x00, 0x00, 0x32, 0x00, 0x1f, 0x0a, 0x00, 0x06, 0x00, 0x11, 0x09,
        0x00, 0x12, 0x00, 0x13, 0x08, 0x00, 0x14, 0x0a, 0x00, 0x15, 0x00, 0x16, 0x07, 0x00, 0x17, 0x07,
        0x00, 0x18, 0x01, 0x00, 0x06, 0x3c, 0x69, 0x6e, 0x69, 0x74, 0x3e, 0x01, 0x00, 0x03, 0x28, 0x29,
        0x56, 0x01, 0x00, 0x04, 0x43, 0x6f, 0x64, 0x65, 0x01, 0x00, 0x0f, 0x4c, 0x69, 0x6e, 0x65, 0x4e,
        0x75, 0x6d, 0x62, 0x65, 0x72, 0x54, 0x61, 0x62, 0x6c, 0x65, 0x01, 0x00, 0x12, 0x4c, 0x6f, 0x63,
        0x61, 0x6c, 0x56, 0x61, 0x72, 0x69, 0x61, 0x62, 0x6c, 0x65, 0x54, 0x61, 0x62, 0x6c, 0x65, 0x01,
        0x00, 0x04, 0x74, 0x68, 0x69, 0x73, 0x01, 0x00, 0x31, 0x4c, 0x6e, 0x61, 0x6d, 0x65, 0x2f, 0x66,
        0x65, 0x6c, 0x69, 0x78, 0x62, 0x65, 0x63, 0x6b, 0x65, 0x72, 0x2f, 0x74, 0x61, 0x6c, 0x6b, 0x73,
        0x2f, 0x63, 0x6c, 0x61, 0x73, 0x73, 0x6c, 0x6f, 0x61, 0x64, 0x65, 0x72, 0x2f, 0x47, 0x72, 0x65,
        0x65, 0x74, 0x65, 0x72, 0x43, 0x6c, 0x61, 0x73, 0x73, 0x3b, 0x01, 0x00, 0x08, 0x3c, 0x63, 0x6c,
        0x69, 0x6e, 0x69, 0x74, 0x3e, 0x01, 0x00, 0x0a, 0x53, 0x6f, 0x75, 0x72, 0x63, 0x65, 0x46, 0x69,
        0x6c, 0x65, 0x01, 0x00, 0x11, 0x47, 0x72, 0x65, 0x65, 0x74, 0x65, 0x72, 0x43, 0x6c, 0x61, 0x73,
        0x73, 0x2e, 0x6a, 0x61, 0x76, 0x61, 0x0c, 0x00, 0x07, 0x00, 0x08, 0x07, 0x00, 0x19, 0x0c, 0x00,
        0x1a, 0x00, 0x1b,

        /* ===== Hallo Welt vs Hello ClassLoader! ===== */
        0x01, // Begin UTF8

        //0x00, 0x0b, // Length: 11 bytes
        // H  A     L     L     O           W     E     L     T     !
        //0x48, 0x61, 0x6c, 0x6c, 0x6f, 0x20, 0x57, 0x65, 0x6c, 0x74, 0x21,

        0x00, 0x12, // Length: 18 bytes
        // H    A     L     L     O           C     L     A     S     S     L     O     A     D     E     R     !
        0x48, 0x61, 0x6c, 0x6c, 0x6f, 0x20, 0x43, 0x6c, 0x61, 0x73, 0x73, 0x6c, 0x6f, 0x61, 0x64, 0x65, 0x72, 0x21,

        /* ============================================ */


        0x07, 0x00, 0x1c, 0x0c, 0x00, 0x1d, 0x00, 0x1e,


        /* ========= Name of the Class (name/felixbecker/talks/classloader/GreeterClass) ========= */

        0x01, // UTF 8
        0x00, 0x2f, // 47 bytes

        // n  a     m     e     /     f     e     l     i     x     b     e     c     k     e     r
        0x6e, 0x61, 0x6d, 0x65, 0x2f, 0x66, 0x65, 0x6c, 0x69, 0x78, 0x62, 0x65, 0x63, 0x6b, 0x65, 0x72,
        // /  t     a     l     k     s     /     c     l     a     s     s     l     o     a     d
        0x2f, 0x74, 0x61, 0x6c, 0x6b, 0x73, 0x2f, 0x63, 0x6c, 0x61, 0x73, 0x73, 0x6c, 0x6f, 0x61, 0x64,
        // e  r     /     G     r     e     e     t     e     r     C     l     a     s     s
        0x65, 0x72, 0x2f, 0x47, 0x72, 0x65, 0x65, 0x74, 0x65, 0x72, 0x43, 0x6c, 0x61, 0x73, 0x73,

        /* ======================================================================================= */


        0x01, // UTF 8
        0x00, 0x10, // 16 bytes

        // j  a     v     a     /     l     a     n     g     /     O     b     j     e     c     t
        0x6a, 0x61, 0x76, 0x61, 0x2f, 0x6c, 0x61, 0x6e, 0x67, 0x2f, 0x4f, 0x62, 0x6a, 0x65, 0x63, 0x74,

        0x01, // UTF 8
        0x00, 0x10, // 16 bytes

        // j  a     v     a     /     l     a     n     g     /     S     y     s     t     e     m
        0x6a, 0x61, 0x76, 0x61, 0x2f, 0x6c, 0x61, 0x6e, 0x67, 0x2f, 0x53, 0x79, 0x73, 0x74, 0x65, 0x6d,


        0x01, // UTF 8
        0x00, 0x03, // 3 bytes

        // o  u     t
        0x6f, 0x75, 0x74,


        /* ===== java/io/PrintStream ===== */

        0x01, // Begin UTF8

        0x00, 0x15, // Length 21 bytes

        // L  j     a     v     a     /     i     o     /
        0x4c, 0x6a, 0x61, 0x76, 0x61, 0x2f, 0x69, 0x6f, 0x2f,
        // P  r     i     n     t     S     t     r     e     a     m     ;
        0x50, 0x72, 0x69, 0x6e, 0x74, 0x53, 0x74, 0x72, 0x65, 0x61, 0x6d, 0x3b,


        0x01, // Begin UTF8

        0x00, 0x13, // Length 19 bytes

        // j  a     v     a     /     i     o     /
        0x6a, 0x61, 0x76, 0x61, 0x2f, 0x69, 0x6f, 0x2f,
        // P  r     i     n     t     S     t     r     e     a     m
        0x50, 0x72, 0x69, 0x6e, 0x74, 0x53, 0x74, 0x72, 0x65, 0x61, 0x6d,

        //* =============================== */

        0x01, 0x00, 0x07, 0x70, 0x72, 0x69, 0x6e, 0x74, 0x6c, 0x6e, 0x01,
        0x00, 0x15, 0x28, 0x4c, 0x6a, 0x61, 0x76, 0x61, 0x2f, 0x6c, 0x61, 0x6e, 0x67, 0x2f, 0x53, 0x74,
        0x72, 0x69, 0x6e, 0x67, 0x3b, 0x29, 0x56, 0x00, 0x21, 0x00, 0x05, 0x00, 0x06, 0x00, 0x00, 0x00,
        0x00, 0x00, 0x02, 0x00, 0x01, 0x00, 0x07, 0x00, 0x08, 0x00, 0x01, 0x00, 0x09, 0x00, 0x00, 0x00,
        0x2f, 0x00, 0x01, 0x00, 0x01, 0x00, 0x00, 0x00, 0x05, 0x2a, 0xb7, 0x00, 0x01, 0xb1, 0x00, 0x00,
        0x00, 0x02, 0x00, 0x0a, 0x00, 0x00, 0x00, 0x06, 0x00, 0x01, 0x00, 0x00, 0x00, 0x03, 0x00, 0x0b,
        0x00, 0x00, 0x00, 0x0c, 0x00, 0x01, 0x00, 0x00, 0x00, 0x05, 0x00, 0x0c, 0x00, 0x0d, 0x00, 0x00,
        0x00, 0x08, 0x00, 0x0e, 0x00, 0x08, 0x00, 0x01, 0x00, 0x09, 0x00, 0x00, 0x00, 0x25, 0x00, 0x02,
        0x00, 0x00, 0x00, 0x00, 0x00, 0x09, 0xb2, 0x00, 0x02, 0x12, 0x03, 0xb6, 0x00, 0x04, 0xb1, 0x00,
        0x00, 0x00, 0x01, 0x00, 0x0a, 0x00, 0x00, 0x00, 0x0a, 0x00, 0x02, 0x00, 0x00, 0x00, 0x06, 0x00,
        0x08, 0x00, 0x07, 0x00, 0x01, 0x00, 0x0f, 0x00, 0x00, 0x00, 0x02, 0x00, 0x10
    };


    public Class<?> createSyntheticGreeterClass(){
        return defineClass("name.felixbecker.talks.classloader.GreeterClass", charToByteArray(greeterClassBytes), 0, greeterClassBytes.length);
    }

    private byte[] charToByteArray(char[] chars){

        byte[] bytes = new byte[chars.length];
        for(int i = 0; i < bytes.length; i++){
            bytes[i] = (byte) chars[i];
        }

        return bytes;

    }
}
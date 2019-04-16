package name.felixbecker.aacl.demo01;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import static org.objectweb.asm.Opcodes.*;

public class SyntheticClassGenerator {
	public static byte[] generateClass(String name) {
		final ClassWriter classWriter = new ClassWriter(0); // FIXME
		classWriter.visit(V12, ACC_PUBLIC, name.replaceAll("\\.", "/"), null, "java/lang/Object", null);
		
		final MethodVisitor constructorMethodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
		constructorMethodVisitor.visitMaxs(2, 1);
		constructorMethodVisitor.visitVarInsn(ALOAD, 0); // push `this` to the operand stack
		constructorMethodVisitor.visitMethodInsn(INVOKESPECIAL, Type.getInternalName(Object.class), "<init>", "()V",false); // call the constructor
		constructorMethodVisitor.visitInsn(RETURN);
		constructorMethodVisitor.visitEnd();

		final MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "get", "()Ljava/lang/Object;", null, null);
		methodVisitor.visitMaxs(2, 1);
		methodVisitor.visitTypeInsn(NEW, Type.getInternalName(Object.class));
		methodVisitor.visitInsn(DUP);
		methodVisitor.visitMethodInsn(INVOKESPECIAL, Type.getInternalName(Object.class), "<init>", "()V", false);
		methodVisitor.visitInsn(ARETURN);
		methodVisitor.visitEnd();

		classWriter.visitEnd();
		return classWriter.toByteArray();
	}
}

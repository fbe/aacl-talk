package name.felixbecker.aacl.demo01.classgenerator;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import java.util.Random;

import static org.objectweb.asm.Opcodes.*;

public class BrokenClassGenerator {

	public enum DamageType {
		SELF_INHERITANCE,
		BYTE_FLIP
	}

	public static byte[] generateBrokenClass(String name, DamageType damageType) {
		final ClassWriter classWriter = new ClassWriter(0); // FIXME

		if(damageType == DamageType.SELF_INHERITANCE) {
			classWriter.visit(V11, ACC_PUBLIC, name.replaceAll("\\.", "/"), null, name.replaceAll("\\.", "/"), null);
		} else {
			classWriter.visit(V11, ACC_PUBLIC, name.replaceAll("\\.", "/"), null, "java/lang/Object", null);
		}
		
		final MethodVisitor constructorMethodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
		constructorMethodVisitor.visitMaxs(2, 1);
		constructorMethodVisitor.visitVarInsn(ALOAD, 0); // push `this` to the operand stack
		constructorMethodVisitor.visitMethodInsn(INVOKESPECIAL, Type.getInternalName(Object.class), "<init>", "()V",false); // call the constructor
		constructorMethodVisitor.visitInsn(RETURN);
		constructorMethodVisitor.visitEnd();

		for(int i = 0; i < 50; i++) {
			final MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "get"+i, "()Ljava/lang/Object;", null, null);
			methodVisitor.visitMaxs(2, 1);
			methodVisitor.visitTypeInsn(NEW, Type.getInternalName(Object.class));
			methodVisitor.visitInsn(DUP);
			methodVisitor.visitMethodInsn(INVOKESPECIAL, Type.getInternalName(Object.class), "<init>", "()V", false);
			methodVisitor.visitInsn(ARETURN);
			methodVisitor.visitEnd();
		}

		classWriter.visitEnd();

		byte[] result = classWriter.toByteArray();

		if(damageType == DamageType.BYTE_FLIP) {
			final Random random = new Random();




			int byteToPatch = random.nextInt(result.length);


			System.err.println("Patching byte "+byteToPatch+": (" + String.format("%02x", result[byteToPatch]) + ")");
			result[byteToPatch] = (byte)(~result[byteToPatch]);
			System.err.println("Patched byte "+byteToPatch+": (" + String.format("%02x", result[byteToPatch]) + ")");
		}

		return result;
	}
}

package com.example.override.bytecode;

import cpw.mods.fml.relauncher.IClassTransformer;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.RETURN;

import java.util.Iterator;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.*;

public class ClassTransformer implements IClassTransformer
{

	@Override
	public byte[] transform(String arg0, byte[] arg2) {

		if (arg0.equals("aan")) {
			System.out.println("********* INSIDE OBFUSCATED ITEM TRANSFORMER ABOUT TO PATCH: " + arg0);
			return patchClassASM(arg0, arg2, true);
		}

		if (arg0.equals("net.minecraft.src.Item")) {
			System.out.println("********* INSIDE ITEM TRANSFORMER ABOUT TO PATCH: " + arg0);
			return patchClassASM(arg0, arg2, false);
		}
		return arg2;
	}

	public byte[] patchClassASM(String name, byte[] bytes, boolean obfuscated) {

		String targetMethodName = obfuscated ? "a" : "addInformation";
		String targetMethodDesc = obfuscated
			? "(Laam;Ljava/util/List;)V"
			: "(Lnet/minecraft/src/ItemStack;Ljava/util/List;)V";

		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(bytes);
		classReader.accept(classNode, 0);

		for (Object me : classNode.methods) {
			MethodNode m = (MethodNode) me;
			System.out.println("Method: " + m.name + " " + m.desc);

			if (m.name.equals(targetMethodName) && m.desc.equals(targetMethodDesc)) {
				System.out.println("Found target method, replacing...");

				// clear existing method body
				m.instructions.clear();
				m.tryCatchBlocks.clear();

				InsnList insn = new InsnList();

				// load parameters
				insn.add(new VarInsnNode(ALOAD, 0)); // Item (this)
				insn.add(new VarInsnNode(ALOAD, 1)); // ItemStack
				insn.add(new VarInsnNode(ALOAD, 2)); // List

				// call static override
				insn.add(new MethodInsnNode(
					INVOKESTATIC,
					"com/example/override/bytecode/OverrideItemInformation",
					"addInformation",
					obfuscated
						? "(Laan;Laam;Ljava/util/List;)V"
						: "(Lnet/minecraft/src/Item;Lnet/minecraft/src/ItemStack;Ljava/util/List;)V"
				));

				// return
				insn.add(new InsnNode(RETURN));

				m.instructions.add(insn);

				System.out.println("Patch complete.");
				break;
			}
		}

		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		classNode.accept(writer);
		return writer.toByteArray();
	}


}

/*
 * Copyright (c) ga 2013
 * <a href="www.ga.test">我们会一起努力</a>
 * Create Date : 2013-11-7
 */

package com.ga.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.springframework.asm.ClassReader;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.ClassWriter;
import org.springframework.asm.Label;
import org.springframework.asm.MethodVisitor;
import org.springframework.asm.Opcodes;
import org.springframework.asm.Type;

/**
 * @description
 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
 * @version 1.0, 2013-11-7
 * @see
 * @since gadvice1.0
 */
public final class Classes {

	private static boolean sameType(Type[] types, Class<?>[] clazzes) {
		if (types.length != clazzes.length) {
			return false;
		}
		for (int i = 0; i < types.length; i++) {
			if (!Type.getType(clazzes[i]).equals(types[i])) {
				return false;
			}
		}
		return true;
	}

	public static Map<String,Object> getMethodParamNames(final Method m) {
		final Map<String,Object> resultMap = new HashMap<String,Object>();
		final  Class<?>[] claaz = m.getParameterTypes();
		final int paramSize = claaz.length ;
//		final String[] paramNames = new String[m.getParameterTypes().length];
		final String n = m.getDeclaringClass().getName();
		final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		ClassReader cr = null;
		try {
			cr = new ClassReader(n);
		} catch (IOException e) {
			e.printStackTrace();
		}
		cr.accept(new ClassVisitor(Opcodes.ASM4, cw) {
			@Override
			public MethodVisitor visitMethod(final int access,
					final String name, final String desc,
					final String signature, final String[] exceptions) {
				final Type[] args = Type.getArgumentTypes(desc);
				// 方法名相同并且参数个数相同
				if (!name.equals(m.getName())
						|| !sameType(args, claaz)) {
					return super.visitMethod(access, name, desc, signature,
							exceptions);
				}
				MethodVisitor v = cv.visitMethod(access, name, desc, signature,
						exceptions);
				return new MethodVisitor(Opcodes.ASM4, v) {
					public void visitLocalVariable(String name, String desc,
							String signature, Label start, Label end, int index) {
						int i = index - 1;
						// 如果是静态方法，则第一就是参数
						// 如果不是静态方法，则第一个是"this"，然后才是方法的参数
						if (Modifier.isStatic(m.getModifiers())) {
							i = index;
						}
						if (i >= 0 && i < paramSize) {
//							paramNames[i] = name;
							resultMap.put(name, claaz[i]);
						}
						super.visitLocalVariable(name, desc, signature, start,
								end, index);
					}
				};
			}
		}, 0);
		return resultMap;
	}

}

/*
 * Copyright 2015 SATO taichi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package io.gige.junit.example;

import static org.junit.Assert.assertTrue;
import io.gige.CompilationResult;
import io.gige.CompilerContext;
import io.gige.Compilers;
import io.gige.TestSource;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * @author taichi
 */
@RunWith(Theories.class)
public class UseTheories {

	@DataPoints
	public static Compilers.Type[] jdk = Compilers.Type.values();

	CompilerContext setUp(CompilerContext context) {
		return context.setSourcePath("src/test/java", "src/test/resources")
				.set(diag -> System.out.println(diag));
	}

	@Theory
	public void test(Compilers.Type type) throws Exception {
		System.out.println(type);
		try (CompilerContext context = new CompilerContext(type)) {
			CompilationResult result = setUp(context)
					.setUnits(TestSource.class).compile();
			assertTrue(result.success());
		}
	}
}

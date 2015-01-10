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
package io.gige.util;

import static org.junit.Assert.assertEquals;
import io.gige.CompilationResult;
import io.gige.CompilerContext;
import io.gige.Compilers;
import io.gige.TestSource;
import io.gige.junit.CompilerRunner;

import java.util.HashMap;
import java.util.Locale;
import java.util.stream.Stream;

import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author taichi
 */
@RunWith(CompilerRunner.class)
public class TypeHierarchyTest {

	@Compilers
	CompilerContext context;

	@Before
	public void setUp() throws Exception {
		this.context.setCharset("UTF-8").set(Locale.JAPANESE)
				.setSourcePath("src/test/java").setUnits(TestSource.class)
				.set(diag -> System.out.println(diag));
	}

	@Test
	public void test() throws Exception {
		CompilationResult result = this.context.compile();

		TypeElement element = result.getTypeElement(HashMap.class).orElseThrow(
				AssertionError::new);
		Stream<String> actual = TypeHierarchy
				.of(result.getEnvironment(), element)
				.map(TypeElement::getQualifiedName).map(Name::toString);
		Stream<String> expected = Stream.of("java.util.HashMap",
				"java.util.AbstractMap", "java.lang.Object");
		Zipper.of(expected, actual, (l, r) -> {
			assertEquals(l, r);
			return l + " " + r;
		}).forEach(System.out::println);
	}

}

/*
 * Copyright 2012-2013 the original author or authors.
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

package org.springframework.boot.actuate.autoconfigure;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerPropertiesAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Dave Syer
 * 
 */
@Ignore("Unignore me when security configuration can be made conditional")
public class SpringApplicationHierarchyTests {

	private ConfigurableApplicationContext context;

	@After
	public void after() {
		if (this.context != null) {
			this.context.close();
		}
	}

	@Test
	public void testParent() {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(Child.class);
		builder.parent(Parent.class);
		this.context = builder.run();
	}

	@Test
	public void testChild() {
		this.context = new SpringApplicationBuilder(Parent.class).child(Child.class)
				.run();
	}

	@EnableAutoConfiguration
	public static class Child {
	}

	@EnableAutoConfiguration(exclude = { ServerPropertiesAutoConfiguration.class })
	public static class Parent {
	}

}
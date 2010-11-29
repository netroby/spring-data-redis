/*
 * Copyright 2010 the original author or authors.
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
package org.springframework.data.keyvalue.redis.util;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized.Parameters;
import org.springframework.data.keyvalue.redis.Person;
import org.springframework.data.keyvalue.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.keyvalue.redis.core.RedisTemplate;

/**
 * Integration test for RedisMap.
 * 
 * @author Costin Leau
 */
public class RedisMapTests extends AbstractRedisMapTests<Object, Object> {

	public RedisMapTests(ObjectFactory<Object> keyFactory, ObjectFactory<Object> valueFactory, RedisTemplate template) {
		super(keyFactory, valueFactory, template);
	}

	@Override
	RedisMap<Object, Object> createMap() {
		String redisName = getClass().getName();
		return new DefaultRedisMap<Object, Object>(redisName, template);
	}

	@Parameters
	public static Collection<Object[]> testParams() {
		// create Jedis Factory
		ObjectFactory<String> stringFactory = new StringObjectFactory();
		ObjectFactory<Person> personFactory = new PersonObjectFactory();

		JedisConnectionFactory jedisConnFactory = new JedisConnectionFactory();
		jedisConnFactory.setPooling(false);
		jedisConnFactory.afterPropertiesSet();

		RedisTemplate<String, String> stringTemplate = new RedisTemplate<String, String>(jedisConnFactory);
		RedisTemplate<String, Person> personTemplate = new RedisTemplate<String, Person>(jedisConnFactory);

		//		JredisConnectionFactory jredisConnFactory = new JredisConnectionFactory();
		//		jredisConnFactory.setPooling(false);
		//		jredisConnFactory.afterPropertiesSet();
		//
		//		RedisTemplate<String, String> stringTemplateJR = new RedisTemplate<String, String>(jredisConnFactory);
		//		RedisTemplate<String, Person> personTemplateJR = new RedisTemplate<String, Person>(jredisConnFactory);

		return Arrays.asList(new Object[][] { { stringFactory, stringFactory, stringTemplate },
				{ personFactory, personFactory, personTemplate } });
	}
}
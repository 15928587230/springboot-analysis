package owinfo.analysis._8PropertySource.EnumerablePropertySource;

import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * @see org.springframework.core.env.SimpleCommandLineArgsParser
 */
public class SimpleCommandLineArgsPropertyTest {

	/**
	 * name: commandLineArgs
	 * source: CommandLineArgs（已知参数和未知参数）
	 * private final Map<String, List<String>> optionArgs = new HashMap(); (集合中value可以重复)
	 * private final List<String> nonOptionArgs = new ArrayList(); (重复存入)
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		args = new String[]{"--spring.profiles.active=dev", "--spring.profiles.active=dev"};

		SimpleCommandLinePropertySource commandLineArgs =
				new SimpleCommandLinePropertySource(args);

		//...十进制61 代表字符 =
		int indexOf = "spring.profiles.active=dev".indexOf(61);
		System.out.println(indexOf);

		String[] propertyNames = commandLineArgs.getPropertyNames();
		for (int i = 0; i < propertyNames.length; i++) {
			System.out.println(propertyNames[i]);
			System.out.println(commandLineArgs.getProperty(propertyNames[i]));
		}


		Map<String, Object> map = new HashMap<>();
		map.put(null, "pjj");
		map.put("age", 23);
		MapPropertySource mapPropertySource = new MapPropertySource("mapProperties", map);
		System.out.println(mapPropertySource.getProperty(null));//pjj
		System.out.println(mapPropertySource.getProperty("age"));//23
	}
}

package owinfo.analysis._9PropertyResolver.PlaceholderResolver;

import org.springframework.util.PropertyPlaceholderHelper;

import java.io.IOException;
import java.util.Properties;

/**
 * @see org.springframework.util.PropertyPlaceholderHelper
 */
public class PlaceHolderHelperTest {

	public static void main(String[] args) throws IOException {

		PropertyPlaceholderHelper propertyPlaceholderHelper
				= new PropertyPlaceholderHelper("${", "}");

		Properties properties = new Properties();
		properties.load(PlaceHolderHelperTest.class.getClassLoader().getResourceAsStream("Placeholder.properties"));

		/**
		 *  循环替换 ${name}${age}${sex} = pjj${age}${sex} = pjj23${sex}= pjj123male
		 */
		String placeholders = propertyPlaceholderHelper.replacePlaceholders("${name}${age}${sex}", properties);
		System.out.println(placeholders);
	}
}

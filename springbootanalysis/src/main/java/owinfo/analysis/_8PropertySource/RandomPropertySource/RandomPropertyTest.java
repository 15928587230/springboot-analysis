package owinfo.analysis._8PropertySource.RandomPropertySource;

import org.springframework.boot.env.RandomValuePropertySource;

/**
 *  name: (random. + suffix) suffix =int,long...
 *  source: new Random()
 */
public class RandomPropertyTest {

    public static void main(String[] args) {

        RandomValuePropertySource random = new RandomValuePropertySource();
        System.out.println(random.getProperty("random.int"));
        System.out.println(random.getProperty("random.long"));
        System.out.println(random.getProperty("random.uuid"));
    }
}

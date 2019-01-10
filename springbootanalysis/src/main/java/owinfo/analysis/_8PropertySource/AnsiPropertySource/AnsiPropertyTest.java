package owinfo.analysis._8PropertySource.AnsiPropertySource;

import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiElement;
import org.springframework.boot.ansi.AnsiPropertySource;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.util.StringUtils;

import java.util.*;

public class AnsiPropertyTest {

    private static Map<String, Set<? extends Enum>> maps;

    public static void main(String[] args) {
        AnsiPropertySource ansiPropertySource = new AnsiPropertySource("AnsiProperty", false);
        System.out.println(ansiPropertySource);

        Object property = ansiPropertySource.getProperty("AnsiColor.RED");
        System.out.println(property);

        boolean b = ansiPropertySource.containsProperty("AnsiColor.RED");
        System.out.println(b == (property!= null)); //包含 用该属性不为空来判断


        System.out.println(getProperty("AnsiColor.BLACK"));
        System.out.println(containProperty("AnsiColor.RED"));
    }


    public static boolean containProperty(String name) {
        return getProperty(name) != null;
    }

    /**
     *  AnsiPropertySource处理流程
     *      通过枚举初始化资源Map<String, Set<Enum>>
     *          获取传入属性对应的枚举列表(传入属性分为几部分)
     */
    public static Object getProperty(String name) {
        if (StringUtils.hasLength(name)) {
            Iterator<String> iterator = maps.keySet().iterator();

            while (iterator.hasNext()) {
                String next = iterator.next();
                if (name.startsWith(next)) {
                    Set<? extends Enum> enums = maps.get(next);
                    String suffix = name.substring(next.length());
                    Iterator<? extends Enum> enumSet = enums.iterator();

                    while (enumSet.hasNext()) {
                        Enum anEnum = enumSet.next();
                        if (suffix.equals(anEnum.name())) {
                            return ((AnsiElement)anEnum).toString();
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     *  初始化
     */
    static {
        Map<String, Set<? extends Enum>> map = new HashMap<>();
        map.put("AnsiColor.", new HashSet<>(Arrays.asList(AnsiColor.values())));
        map.put("AnsiStyle.", new HashSet<>(Arrays.asList(AnsiStyle.values())));
        maps = map;
    }
}

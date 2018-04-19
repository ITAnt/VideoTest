package fm.jiecao.jcvideoplayer_lib;

/**
 * Created by zhanzc on 2018/3/9.
 */

public class VideoInfo {
    private String name;
    private String path;

    public VideoInfo(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

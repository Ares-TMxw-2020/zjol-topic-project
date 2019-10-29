package zjol.com.cn.topic.bean;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2019-10-29
 * @describe:
 */

public class TopicSearchBean {

    /**
     * has_more : true
     * elements : [{"id":"aa","name":"家有萌宠","url":"url","highlight_fields":{"list_title":[{"content":"这","start":0,"end":1}]}}]
     */

    private boolean has_more;
    private List<ElementsBean> elements;

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public List<ElementsBean> getElements() {
        return elements;
    }

    public void setElements(List<ElementsBean> elements) {
        this.elements = elements;
    }

    public static class ElementsBean {
        /**
         * id : aa
         * name : 家有萌宠
         * url : url
         * highlight_fields : {"list_title":[{"content":"这","start":0,"end":1}]}
         */

        private String id;
        private String name;
        private String url;
        private HighlightFieldsBean highlight_fields;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public HighlightFieldsBean getHighlight_fields() {
            return highlight_fields;
        }

        public void setHighlight_fields(HighlightFieldsBean highlight_fields) {
            this.highlight_fields = highlight_fields;
        }

        public static class HighlightFieldsBean {
            private List<ListTitleBean> list_title;

            public List<ListTitleBean> getList_title() {
                return list_title;
            }

            public void setList_title(List<ListTitleBean> list_title) {
                this.list_title = list_title;
            }

            public static class ListTitleBean {
                /**
                 * content : 这
                 * start : 0
                 * end : 1
                 */

                private String content;
                private int start;
                private int end;

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public int getStart() {
                    return start;
                }

                public void setStart(int start) {
                    this.start = start;
                }

                public int getEnd() {
                    return end;
                }

                public void setEnd(int end) {
                    this.end = end;
                }
            }
        }
    }
}

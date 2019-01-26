package audiokitabs.safaroff.com.audiokitab.Model;

import java.util.List;

public class Books {
    private String next;
    private String count;
    private List<Results> results = null;

    public Books() {
    }

    public Books(String next, String count, List<Results> results) {
        this.next = next;
        this.count = count;
        this.results = results;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }
}

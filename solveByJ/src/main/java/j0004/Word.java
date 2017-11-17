package j0004;

public class Word implements Comparable<Word>{

    private String word;

    private Integer count;

    public Word() {
    }

    public Word(String word) {
        this.word = word.toLowerCase();
        this.count = 1;
    }

    public Word(String word, Integer count) {
        this.word = word.toLowerCase();
        this.count = count;
    }

    public String getWord() {
        return word.toLowerCase();
    }

    public void setWord(String word) {
        this.word = word.toLowerCase();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void plus(){
        this.count += 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word1 = (Word) o;

        return word.equals(word1.word);
    }

    @Override
    public int hashCode() {
        return word.hashCode();
    }

    @Override
    public String toString() {
        return "单词\""+word+"\"出现了"+count+"次";
    }

    public int compareTo(Word o) {
        int count1 = this.count;
        int count2 = o.count;
        return count2-count1;
    }
}

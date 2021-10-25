private static class NameComparator implements Comparator<Person> {

    private Collator collator;

    public NameComparator() {
        this.collator = Collator.getInstance(new Locale("es"));
        collator.setStrength(Collator.TERTIARY);
    }

    @Override
    public int compare(Person o1, Person o2) {
        return collator.compare(o1.getName(), o2.getName());
    }
}
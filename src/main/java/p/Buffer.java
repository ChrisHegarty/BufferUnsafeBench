package p;

interface Buffer {
    void bigEndian(boolean big);
    int getInt(int index);
    void putInt(int index, int value);
}

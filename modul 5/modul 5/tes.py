import os

def remove_duplicates(data):
    seen = set()
    new_data = []

    for line in data:
        if line not in seen:
            new_data.append(line)
            seen.add(line)

    return new_data

def main():
    data = [
        "Nama    NIM     Semester", 
        "Andi   11119       1",
        "Bima   11112       1",
        "Rahma  11131       3",
        "Zeno   11198       9",
        "Rahma  11131       3",
        "Andi   11119       1"]
    print ("\n=============================\nData Sebelum\n=============================\n")
    print(data)
    new_data = remove_duplicates(data)

    with open("data_mahasiswa.txt", "w") as f:
        for line in new_data:
            f.write(line + "\n")
    print ("\n=============================\nData Sesudah Remove Duplicate\n=============================\n")
    with open("data_mahasiswa.txt", "r") as f:
        print (f.read())

if __name__ == "__main__":
    main()


import os

def read_data():
    with open("data.txt", "r") as f:
        data = f.readlines()
    return data


def create_data():
   
    nim = input("Masukkan NIM: ")
    nama = input("Masukkan nama: ")
    matkul = input("Masukkan mata kuliah: ")
    semester = input("Masukkan semester: ")

    
    with open("data.txt", "a") as f:
        f.write(f"{nim},{nama},{matkul},{semester}\n")


def read_all_data():
   
    data = read_data()

   
    for d in data:
        print(d, end="")


def update_data():
    
    nim = input("Masukkan NIM yang ingin diubah: ")
    nama = input("Masukkan nama baru: ")
    matkul = input("Masukkan mata kuliah baru: ")
    semester = input("Masukkan semester baru: ")

    
    data = read_data()

    
    for i in range(len(data)):
        if data[i].split(",")[0] == nim:
            data[i] = f"{nim},{nama},{matkul},{semester}\n"
            break

    
    with open("data.txt", "w") as f:
        for d in data:
            f.write(d)


def delete_data():
    
    nim = input("Masukkan NIM yang ingin dihapus: ")

    
    data = read_data()

    
    for i in range(len(data)):
        if data[i].split(",")[0] == nim:
            del data[i]
            break

    
    with open("data.txt", "w") as f:
        for d in data:
            f.write(d)

# Fungsi untuk mencari data
def search_data():
    # Membaca input dari pengguna
    nim = input("Masukkan NIM yang ingin dicari: ")

    # Membaca data dari file
    data = read_data()

    # Mencari data yang sesuai
    for d in data:
        if d.split(",")[0] == nim:
            print(d, end="")

# Menu utama
def main():
    while True:
        # Menampilkan menu
        print("=====APLIKASI KELOLA DATA MAHASISWA UMM=====")
        print("1. Tambah Data")
        print("2. Tampilkan Data")
        print("3. Update Data")
        print("4. Hapus Data")
        print("5. Cari Data")
        print("6. Keluar")

        # Memilih menu
        pilihan = input("Pilih menu: ")

        # Melaksanakan pilihan
        if pilihan == "1":
            create_data()
        elif pilihan == "2":
            read_all_data()
        elif pilihan == "3":
            update_data()
        elif pilihan == "4":
            delete_data()
        elif pilihan == "5":
            search_data()
        elif pilihan == "6":
            exit()
        else:
            print("Pilihan tidak valid. Silakan pilih lagi.")

if __name__ == "__main__":
    main()

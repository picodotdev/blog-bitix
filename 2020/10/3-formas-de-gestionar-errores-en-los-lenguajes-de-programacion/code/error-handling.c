int result = create_file(file_name);
if (result == FILE_ALREADY_EXISTS || result == DISK_FULL) {
   printf("The file already exists or disk full");
}

...
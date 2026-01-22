#include <stdio.h>
#include <string.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>

int main() {
    char word[50], meaning[100];
    int fd_req, fd_res;

    mkfifo("request_fifo", 0666);
    mkfifo("response_fifo", 0666);

    printf("Dictionary Server is running...\n");

    while (1) {
        fd_req = open("request_fifo", O_RDONLY);
        read(fd_req, word, sizeof(word));
        close(fd_req);

        if (strcmp(word, "exit") == 0) break;

        if (strcmp(word, "apple") == 0) strcpy(meaning, "A red round fruit.");
        else if (strcmp(word, "linux") == 0) strcpy(meaning, "An open-source kernel.");
        else if (strcmp(word, "fifo") == 0) strcpy(meaning, "First In, First Out communication.");
        else strcpy(meaning, "Word not found in dictionary.");

        fd_res = open("response_fifo", O_WRONLY);
        write(fd_res, meaning, strlen(meaning) + 1);
        close(fd_res);
    }

    unlink("request_fifo");
    unlink("response_fifo");
    return 0;
}


#include <stdio.h>
#include <string.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>

int main() {
    char word[50], meaning[100];
    int fd_req, fd_res;

    printf("Enter a word to look up: ");
    scanf("%s", word);

    fd_req = open("request_fifo", O_WRONLY);
    write(fd_req, word, strlen(word) + 1);
    close(fd_req);

    fd_res = open("response_fifo", O_RDONLY);
    read(fd_res, meaning, sizeof(meaning));
    printf("Meaning: %s\n", meaning);
    close(fd_res);

    return 0;
}


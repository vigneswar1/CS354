#include <stdio.h>
#include <string.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <unistd.h>

#define SERVER_FIFO "server_well_known"

struct request {
    pid_t client_pid;
    char word[50];
};

int main() {
    struct request req;
    char meaning[200], my_fifo_name[50];
    int fd_srv, fd_cli;

    req.client_pid = getpid();
    sprintf(my_fifo_name, "fifo_%d", req.client_pid);
    mkfifo(my_fifo_name, 0666);

    printf("Enter word: ");
    scanf("%s", req.word);

    fd_srv = open(SERVER_FIFO, O_WRONLY);
    write(fd_srv, &req, sizeof(req));
    close(fd_srv);

    fd_cli = open(my_fifo_name, O_RDONLY);
    read(fd_cli, meaning, sizeof(meaning));
    printf("Meaning: %s\n", meaning);
    close(fd_cli);

    unlink(my_fifo_name);
    return 0;
}


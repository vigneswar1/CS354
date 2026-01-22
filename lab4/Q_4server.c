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
    char meaning[200], client_fifo[50];
    int fd_srv, fd_cli;

    mkfifo(SERVER_FIFO, 0666);
    printf("Dictionary Server is active...\n");

    while (1) {
        fd_srv = open(SERVER_FIFO, O_RDONLY);
        if (read(fd_srv, &req, sizeof(req)) > 0) {
            printf("Received request from PID %d: %s\n", req.client_pid, req.word);

            if (strcasecmp(req.word, "apple") == 0) strcpy(meaning, "A red round fruit.");
            else if (strcasecmp(req.word, "linux") == 0) strcpy(meaning, "An open-source kernel.");
            else strcpy(meaning, "Word not found.");

            sprintf(client_fifo, "fifo_%d", req.client_pid);
            fd_cli = open(client_fifo, O_WRONLY);
            write(fd_cli, meaning, strlen(meaning) + 1);
            close(fd_cli);
        }
        close(fd_srv);
    }
    return 0;
}


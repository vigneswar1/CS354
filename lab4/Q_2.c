#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <unistd.h>
#include <string.h>

#define Readend 0
#define Writeend 1

int main() {
    int fd1[2];
    int fd2[2];
    pid_t pid;

    if (pipe(fd1) == -1 || pipe(fd2) == -1) {
        perror("pipe failed");
        exit(EXIT_FAILURE);
    }

    pid = fork();

    if (pid < 0) {
        perror("fork failed");
        exit(EXIT_FAILURE);
    }

    if (pid == 0) {
        int a, b, result;
        char op;

        printf("enter a : ");
        scanf("%d", &a);
        printf("enter b : ");
        scanf("%d", &b);
        printf("enter op : ");
        scanf(" %c", &op);

        close(fd1[Readend]);
        close(fd2[Writeend]);

        write(fd1[Writeend], &a, sizeof(int));
        write(fd1[Writeend], &b, sizeof(int));
        write(fd1[Writeend], &op, sizeof(char));
        close(fd1[Writeend]);

        read(fd2[Readend], &result, sizeof(int));
        printf("result : %d\n", result);
        close(fd2[Readend]);

        exit(EXIT_SUCCESS);
    } 
    else {
        int a, b, result;
        char op;

        close(fd1[Writeend]);
        close(fd2[Readend]);

        read(fd1[Readend], &a, sizeof(int));
        read(fd1[Readend], &b, sizeof(int));
        read(fd1[Readend], &op, sizeof(char));
        close(fd1[Readend]);

        if (op == '+') result = a + b;
        else if (op == '-') result = a - b;
        else if (op == '*') result = a * b;
        else if (op == '/') result = (b != 0) ? a / b : 0;
        else {
            printf("error op\n");
            result = 0;
        }
        write(fd2[Writeend], &result, sizeof(int));
        close(fd2[Writeend]);

        wait(NULL);
        exit(EXIT_SUCCESS);
    }

    return 0;
}


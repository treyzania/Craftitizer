#include <stdio.h>
#include <sys/types.h>
#include <dirent.h>
#include <sys/stat.h>
#include <unistd.h>
#include <string.h>

#define EXEC_DIR_NAME "/exec"
#define DATA_DIR_NAME "/data"
#define WORK_DIR_NAME "/.work"
#define LIVE_DIR_NAME "/live"

#define EXEC_DIR_FLAGS (S_IRWXU | S_IRWXG | S_IROTH | S_IXOTH)
#define DATA_DIR_FLAGS (S_IRWXU | S_IRWXG | S_IROTH | S_IXOTH)
#define WORK_DIR_FLAGS (S_IRWXU)
#define LIVE_DIR_FLAGS (S_IRWXU | S_IRWXG | S_IROTH | S_IXOTH)

char* mallocconcat(char* a, char* b) {

	int len = strlen(a) + strlen(b) + 1;
	char* out = malloc(len);

	strcat(out, a);
	strcat(out, b);

	return out;

}

void makeextdir(char* base, char* ext, int flags) {

	char* fullname = mallocconcat(base, ext);
	if (mkdir(fullname, flags) != 0) {
		printf("Could not make %s dir.", ext);
	}

	free(fullname);

}

int main(int argc, char* argv[]) {

	if (argc != 2) {
		printf("usage: %s <path>\n", argv[0]);
		return -1;
	}

	char* splitpath;
	DIR* splitdir = opendir(splitpath);

	if (splitdir == NULL) {
		perror("opendir");
		return -1;
	}

	makeextdir(splitpath, EXEC_DIR_NAME, EXEC_DIR_FLAGS);
	makeextdir(splitpath, DATA_DIR_NAME, DATA_DIR_FLAGS);
	makeextdir(splitpath, WORK_DIR_NAME, WORK_DIR_FLAGS);
	makeextdir(splitpath, LIVE_DIR_NAME, LIVE_DIR_FLAGS);

}

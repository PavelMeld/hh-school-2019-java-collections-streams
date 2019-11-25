SRC_DIR = src
JAVA_FILES = \
	$(SRC_DIR)/common/Vacancy.java \
	$(SRC_DIR)/common/Person.java \
	$(SRC_DIR)/common/Task.java \
	$(SRC_DIR)/common/PersonService.java \
	$(SRC_DIR)/common/Area.java \
	$(SRC_DIR)/common/ApiPersonDto.java \
	$(SRC_DIR)/common/Company.java \
	$(SRC_DIR)/tasks/Task4.java \
	$(SRC_DIR)/tasks/Task7.java \
	$(SRC_DIR)/tasks/Task2.java \
	$(SRC_DIR)/tasks/Task3.java \
	$(SRC_DIR)/tasks/Task6.java \
	$(SRC_DIR)/tasks/Task8.java \
	$(SRC_DIR)/tasks/Task1.java \
	$(SRC_DIR)/tasks/Task5.java \
	$(SRC_DIR)/Main.java

OUT_DIR=./bin

JAVA_ARGS=-sourcepath $(SRC_DIR):.$(SRC_DIR)/common:$(SRC_DIR)/tasks 

all: clean $(OUT_DIR)
	javac -d $(OUT_DIR) $(JAVA_ARGS) $(JAVA_FILES)

run:
	java -cp $(OUT_DIR) Main

%.class : %.java
	@echo "compile $< to $@"
	javac $(JAVA_ARGS) $<
	
clean:
	find . -name "*.class" -exec rm {} \;

$(OUT_DIR):
	mkdir $@

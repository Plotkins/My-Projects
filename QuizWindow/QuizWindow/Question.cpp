#include "Question.h"
#include <Windows.h>
#include <string.h>
#include <sstream>


Question::Question()
{
}


string Question::toStr()
{
	return "ID: " + to_string(id) + "\nText: " + text + "\nAnswer: " + answer + "\nScore: " + to_string(score) + "\n\n";
}

string Question::toStrPart()
{
	return "ID: " + to_string(id) + "\nText: " + text + "\nScore: " + to_string(score) + "\n\n";
}

string Question::toLine()
{
	return to_string(id) + "|" + text + "|" + answer + "|" + to_string(score);
}

Question::~Question()
{
}

istream & operator >> (istream & in, Question & q)
{
	string s;
	char *sir, *p;
	getline(in, s);
	int id;
	int score;
	
	if (s.length() == 0)
		return in;
	sir = (char*)malloc(sizeof(char)*(s.size() + 1));
	strcpy(sir, s.c_str());
	p = strtok(sir, "|");
	id = stoi(p);

	p = strtok(NULL, "|");
	string text(p);

	p = strtok(NULL, "|");
	string answer(p);

	p = strtok(NULL, "|");
	score = stoi(p);

	q.setId(id);
	q.setText(text);
	q.setAnswer(answer);
	q.setScore(score);

	return in;

}
#pragma once
#include <string>
#include <iostream>
#include <fstream>
using namespace std;
class Question
{
private:
	int id;
	string text;
	string answer;
	int score;
public:
	Question();
	Question(int i,string t,string a,int s):id(i),text(t),answer(a),score(s){}

	int getId() { return this->id; }
	string getText() { return text; }
	string getAnswer() { return answer; }
	int getScore() { return score; }

	void setId(int i) { this->id = i; }
	void setText(string t) { this->text = t; }
	void setAnswer(string a) { this->answer = a; }
	void setScore(int s) { this->score = s; }

	string toStr();
	string toStrPart();
	string toLine();
	~Question();
};

istream & operator >> (istream & in, Question & q);
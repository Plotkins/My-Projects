#pragma once
#include "Question.h"
#include "Participant.h"
#include <vector>
class Repository
{
private:
	vector<Question> questions;
	vector<Participant*> participants;
	string Qfilename;
	string Pfilename;
	void readFromFile();
	void writeToFile();
public:
	Repository();
	Repository(string Qf,string Pf);
	vector<Question> getQuestionsByScore();
	vector<Question> getQuestionsById();
	vector<Participant*> getParticipants() { return participants; }
	void addQuestion(Question q);
	void addScoreToParticipant(Participant* p, int score,int id);
	unsigned int findPosOfQustion(Question s);
	~Repository();
};


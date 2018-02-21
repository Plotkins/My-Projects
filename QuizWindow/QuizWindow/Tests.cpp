#include "Tests.h"


void Tests::testAddQuestion()
{
	Repository repo{};
	Question q{ 1,"alaba","bala",20 };
	repo.addQuestion(q);
	vector<Question> v=repo.getQuestionsById();
	assert(v[0].getId() == q.getId());
}

void Tests::testUpdateScore()
{
	Repository repo{};
	Participant* p = new Participant{ "George" };
	repo.addScoreToParticipant(p, 30, 1);
	assert(p->getScore()==30);
}

void Tests::runTests()
{
	testAddQuestion();
	testUpdateScore();
}

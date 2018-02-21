
#pragma once
#include <exception>
#include <string>

class StartException : public std::exception
{
protected:
	std::string message;

public:
	StartException();
	StartException(const std::string& msg);
	virtual const char* what();
};
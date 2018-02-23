#pragma once
#include <vector>
#include <string>
#include "Coat.h"

class CoatException
{
private:
	std::vector<std::string> errors;

public:
	CoatException(std::vector<std::string> _errors) :errors(_errors){}
	std::vector<std::string> getErrors() const { return this->errors; }
	std::string getErrorsAsString() const;
};

class CoatValidator
{
public:
	CoatValidator() {}
	static void validateSizeAndColour(const std::string size,const std::string colour);
	static void validateCoat(const Coat& c);
};


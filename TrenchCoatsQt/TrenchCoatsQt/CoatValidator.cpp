#include "CoatValidator.h"
#include <string>
#include <vector>

using namespace std;


CoatValidator::CoatValidator()
{
}


std::string CoatException::getErrorsAsString() const
{
	string err;
	for (auto e : this->errors)
		err += e;
	return err;
}

void CoatValidator::validateSizeAndColour(const std::string size, const std::string colour)
{
	vector<string> errors;
	if (colour.size() < 3)
		errors.push_back("The colour name cannot be les than 3 characters.\n");
	int ok = 0;
	vector<string> sizes{ "xs","s","m","l","xl","xxl" };
	for (auto i : sizes)
		if (size == i)
			ok = 1;
	if (ok == 1)
		errors.push_back("The size is not one of the following: xs, s, m, l, xl, xxl.\n");
	
	if (errors.size() > 0)
		throw CoatException(errors);
}

void CoatValidator::validateCoat(const Coat & c)
{
	vector<string> errors;
	if (c.getColour().size() < 3)
		errors.push_back("The colour name cannot be less than 3 characters.\n");
	int ok = 0;
	vector<string> sizes{ "xs","s","m","l","xl","xxl" };
	for (auto i : sizes)
		if (c.getSize() == i)
			ok = 1;
	if (ok == 1)
		errors.push_back("The size is not one of the following: xs, s, m, l, xl, xxl.\n");
	if (c.getPrice() < 0)
		errors.push_back("The price cannot be negative.\n");
	if (c.getQuantity() < 0)
		errors.push_back("The quantity cannot be negative.\n");
	if (c.getLength() < 0)
		errors.push_back("The length cannot be negative.\n");
	int posWWW = c.getLink().find("www");
	int posHTTP = c.getLink().find("http");
	if (posWWW != 0 || posHTTP != 0)
		errors.push_back("The photo link must start with one of the following: www or http.\n");

	if (errors.size() > 0)
		throw CoatException(errors);
}

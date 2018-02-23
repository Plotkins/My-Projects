#include "Coat.h"
#include <Windows.h>

Coat::Coat() : size(""), colour(""), price(0), quantity(0),length(0), link("0") {};

Coat::Coat(const std::string & size, const std::string & colour, const float & price, const int & quantity,const float& length, const std::string & link)
{
	this->size = size;
	this->colour = colour;
	this->price = price;
	this->quantity = quantity;
	this->length = length;
	this->link = link;
}

bool Coat::operator==(const Coat & c)//lab activity
{
	if (c.getColour() == this->colour && c.getSize() == this->size)
		return true;
	return false;
}

bool Coat::operator<(const float length)
{
	if (this->length < length)
		return true;
	return false;
}

void Coat::openImg()
{
	ShellExecuteA(NULL, NULL, "chrome.exe", this->getLink().c_str(), NULL, SW_SHOWMAXIMIZED);

}

Coat::~Coat()
{
}

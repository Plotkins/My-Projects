#include "Exceptions.h"

StartException::StartException() : exception{}, message{ "" }
{
}

StartException::StartException(const std::string & msg) : message{ msg }
{
}

const char * StartException::what()
{
	return this->message.c_str();
}
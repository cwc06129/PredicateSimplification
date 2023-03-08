def inToPostFix(s):
    def reject(what): # Produce a readable error
        raise SyntaxError("Expected {}, but got {} at index {}".format(
            what or "EOF", 
            "'{}'".format(tokens[-1]) if tokens else "EOF", 
            len(s) - len(tokens)
        ))

    get = lambda: tokens.pop() if tokens else ""
    put = lambda token: output.append(token)
    match = lambda what: tokens[-1] in what if tokens else what == ""
    expect = lambda what: get() if match(what) else reject(what)

    def suffix():
        token = get()
        term()
        put(token)

    def parens(): 
        expect("(")
        expression(")")

    def term():
        if match(identifier): put(get())
        elif match(unary): suffix()
        elif match("("): parens()
        else: expect("an identifier, a unary operator or an opening parenthesis");

    def expression(terminator):
        term()
        if match(binary): suffix()
        expect(terminator)

    # Define the token groups
    identifier = "abcdefghijklmnopqrstuwxyz"
    identifier += identifier.upper()
    unary = "~";
    binary = "^v→";
    tokens = list(reversed(s)) # More efficient to pop from the end
    output = [] # Will be populated during the parsing
    expression("") # Parse!
    return "".join(output)

print(inToPostFix("((a^b)^(b^c))"))
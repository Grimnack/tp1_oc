import matplotlib.pyplot as plt

plt.xlim([0,125])
# plt.ylim([0,1600000])
plt.xlabel("les instances")
plt.ylabel("les scores")


def lecture(pathname) :
    '''
    Ouvre un fichier et renvoie la liste des eval et la liste des temps cpu
    '''
    fichier = open(pathname,'r')
    lesEvals = []
    lesTemps = []
    for line in fichier :
        couple = line.split(";")
        lesEvals.append(float(couple[0]))
        lesTemps.append(float(couple[1]))
    fichier.close()
    return (lesEvals,lesTemps)

def loadBest(pathname) :
    '''
    Charge le fichier de best score et renvoie la liste des scores
    '''
    fichier = open(pathname,'r')
    lesBests = []
    for line in fichier :
        chaine = line.replace(" ","")
        chaine = chaine.replace("\n","")
        if chaine != "" :
            lesBests.append(float(chaine))
    fichier.close()
    # print(lesBests)
    return lesBests

def traceEvals(pathname) :
    (lesEvals,lesTemps) = lecture(pathname)
    lesBests = loadBest ("src/test/wtbest100b.txt")
    x = []
    x2 = []
    for i in range(len(lesEvals)) :
        x.append(i)
    for i in range(len(lesBests)) :
        x2.append(i)
    plt.bar(x,lesEvals,color="#87CEFA")
    plt.bar(x2,lesBests,color="red")
    plt.show()

# traceEvals("random.stat")
# traceEvals("edd.stat")
# traceEvals("mdd.stat")
traceEvals("hc_first_swap_edd.stat")
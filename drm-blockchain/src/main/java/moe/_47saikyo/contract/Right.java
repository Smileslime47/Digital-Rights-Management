package moe._47saikyo.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.5.2.
 */
@SuppressWarnings("rawtypes")
public class Right extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506040516110af3803806110af83398101604081905261002f91610187565b600061003b86826102bb565b50600180546001600160a01b03191633179055600261005a85826102bb565b50600380546001600160401b0384811668010000000000000000026001600160801b031990921690861617179055600461009482826102bb565b50506005805460ff191660011790555061037a92505050565b634e487b7160e01b600052604160045260246000fd5b600082601f8301126100d457600080fd5b81516001600160401b03808211156100ee576100ee6100ad565b604051601f8301601f19908116603f01168101908282118183101715610116576101166100ad565b816040528381526020925086602085880101111561013357600080fd5b600091505b838210156101555785820183015181830184015290820190610138565b6000602085830101528094505050505092915050565b80516001600160401b038116811461018257600080fd5b919050565b600080600080600060a0868803121561019f57600080fd5b85516001600160401b03808211156101b657600080fd5b6101c289838a016100c3565b965060208801519150808211156101d857600080fd5b6101e489838a016100c3565b95506101f26040890161016b565b94506102006060890161016b565b9350608088015191508082111561021657600080fd5b50610223888289016100c3565b9150509295509295909350565b600181811c9082168061024457607f821691505b60208210810361026457634e487b7160e01b600052602260045260246000fd5b50919050565b601f8211156102b6576000816000526020600020601f850160051c810160208610156102935750805b601f850160051c820191505b818110156102b25782815560010161029f565b5050505b505050565b81516001600160401b038111156102d4576102d46100ad565b6102e8816102e28454610230565b8461026a565b602080601f83116001811461031d57600084156103055750858301515b600019600386901b1c1916600185901b1785556102b2565b600085815260208120601f198616915b8281101561034c5788860151825594840194600190910190840161032d565b508582101561036a5787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b610d26806103896000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c8063bc8018b114610046578063ccaf080414610064578063ff3c1a8f146100c6575b600080fd5b61004e6100ce565b60405161005b919061078b565b60405180910390f35b6100c46100723660046107be565b600680546001810182556000919091527ff652222313e28459528d920b65115c16c04f3efc82aaedc97be59f3f377c0d3f0180546001600160a01b0319166001600160a01b0392909216919091179055565b005b61004e6103d1565b60606000604051806040016040528060018152602001607b60f81b81525090508060006040516020016101029291906108c5565b60408051601f19818403018152919052600154909150819061012c906001600160a01b0316610463565b60405160200161013d92919061090b565b604051602081830303815290604052905080600260405160200161016292919061095e565b60408051601f19818403018152919052600354909150819061018d9067ffffffffffffffff16610644565b60405160200161019e92919061099d565b60408051808303601f1901815291905260035490915081906101d59068010000000000000000900467ffffffffffffffff16610644565b6040516020016101e69291906109f4565b604051602081830303815290604052905080600460405160200161020b929190610a4c565b60408051601f19818403018152919052600554909150819060ff1661024d576040518060400160405280600581526020016466616c736560d81b81525061026b565b604051806040016040528060048152602001637472756560e01b8152505b60405160200161027c929190610a84565b60405160208183030381529060405290508060405160200161029e9190610ac0565b604051602081830303815290604052905060005b6006548110156103a95781600682815481106102d0576102d0610af0565b60009182526020822001546040805163bc8018b160e01b815290516001600160a01b039092169263bc8018b1926004808401938290030181865afa15801561031c573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f191682016040526103449190810190610b1c565b604051602001610355929190610bc9565b60408051601f1981840301815291905260065490925061037790600190610c0e565b8110156103a1578160405160200161038f9190610c27565b60405160208183030381529060405291505b6001016102b2565b50806040516020016103bb9190610c4c565b60408051601f1981840301815291905292915050565b6060600080546103e0906107ee565b80601f016020809104026020016040519081016040528092919081815260200182805461040c906107ee565b80156104595780601f1061042e57610100808354040283529160200191610459565b820191906000526020600020905b81548152906001019060200180831161043c57829003601f168201915b5050505050905090565b604080518082018252601081526f181899199a1a9b1b9c1cb0b131b232b360811b60208201528151602a80825260608281019094526001600160a01b0385169291600091602082018180368337019050509050600360fc1b816000815181106104ce576104ce610af0565b60200101906001600160f81b031916908160001a905350600f60fb1b816001815181106104fd576104fd610af0565b60200101906001600160f81b031916908160001a90535060005b601481101561063b578260048561052f84600c610c72565b6020811061053f5761053f610af0565b1a60f81b6001600160f81b031916901c60f81c60ff168151811061056557610565610af0565b01602001516001600160f81b03191682610580836002610c85565b61058b906002610c72565b8151811061059b5761059b610af0565b60200101906001600160f81b031916908160001a90535082846105bf83600c610c72565b602081106105cf576105cf610af0565b825191901a600f169081106105e6576105e6610af0565b01602001516001600160f81b03191682610601836002610c85565b61060c906003610c72565b8151811061061c5761061c610af0565b60200101906001600160f81b031916908160001a905350600101610517565b50949350505050565b60608160000361066b5750506040805180820190915260018152600360fc1b602082015290565b8160005b8115610695578061067f81610c9c565b915061068e9050600a83610cb5565b915061066f565b60008167ffffffffffffffff8111156106b0576106b0610b06565b6040519080825280601f01601f1916602001820160405280156106da576020820181803683370190505b509050815b851561063b576106f0600182610c0e565b905060006106ff600a88610cb5565b61070a90600a610c85565b6107149088610c0e565b61071f906030610cd7565b905060008160f81b90508084848151811061073c5761073c610af0565b60200101906001600160f81b031916908160001a90535061075e600a89610cb5565b975050506106df565b60005b8381101561078257818101518382015260200161076a565b50506000910152565b60208152600082518060208401526107aa816040850160208701610767565b601f01601f19169190910160400192915050565b6000602082840312156107d057600080fd5b81356001600160a01b03811681146107e757600080fd5b9392505050565b600181811c9082168061080257607f821691505b60208210810361082257634e487b7160e01b600052602260045260246000fd5b50919050565b8054600090600181811c908083168061084257607f831692505b6020808410820361086357634e487b7160e01b600052602260045260246000fd5b818015610877576001811461088c576108b9565b60ff19861689528415158502890196506108b9565b60008881526020902060005b868110156108b15781548b820152908501908301610898565b505084890196505b50505050505092915050565b600083516108d7818460208801610767565b68113a34ba3632911d1160b91b9083019081526108f76009820185610828565b61088b60f21b815260020195945050505050565b6000835161091d818460208801610767565b681137bbb732b9111d1160b91b9083019081528351610943816009840160208801610767565b61088b60f21b60099290910191820152600b01949350505050565b60008351610970818460208801610767565b75113932b3b4b9ba3930ba34b7b7273ab6b132b9111d1160511b9083019081526108f76016820185610828565b600083516109af818460208801610767565b6c1134b9b9bab2aa34b6b2911d1160991b90830190815283516109d981600d840160208801610767565b61088b60f21b600d9290910191820152600f01949350505050565b60008351610a06818460208801610767565b6d1132bc3834b932aa34b6b2911d1160911b9083019081528351610a3181600e840160208801610767565b61088b60f21b600e9290910191820152601001949350505050565b60008351610a5e818460208801610767565b6e113232b9b1b934b83a34b7b7111d1160891b9083019081526108f7600f820185610828565b60008351610a96818460208801610767565b6c1130bb30b4b630b13632911d1160991b90830190815283516109d981600d840160208801610767565b60008251610ad2818460208701610767565b6b226c6963656e736573223a5b60a01b920191825250600c01919050565b634e487b7160e01b600052603260045260246000fd5b634e487b7160e01b600052604160045260246000fd5b600060208284031215610b2e57600080fd5b815167ffffffffffffffff80821115610b4657600080fd5b818401915084601f830112610b5a57600080fd5b815181811115610b6c57610b6c610b06565b604051601f8201601f19908116603f01168101908382118183101715610b9457610b94610b06565b81604052828152876020848701011115610bad57600080fd5b610bbe836020830160208801610767565b979650505050505050565b60008351610bdb818460208801610767565b835190830190610bef818360208801610767565b01949350505050565b634e487b7160e01b600052601160045260246000fd5b81810381811115610c2157610c21610bf8565b92915050565b60008251610c39818460208701610767565b600b60fa1b920191825250600101919050565b60008251610c5e818460208701610767565b615d7d60f01b920191825250600201919050565b80820180821115610c2157610c21610bf8565b8082028115828204841417610c2157610c21610bf8565b600060018201610cae57610cae610bf8565b5060010190565b600082610cd257634e487b7160e01b600052601260045260246000fd5b500490565b60ff8181168382160190811115610c2157610c21610bf856fea2646970667358221220a2aa8606143b0bb67ebc065231cfc790dc2c8ac7ab8ec4a150ee34a988acb69964736f6c63430008190033";

    public static final String FUNC_ADDLICENSE = "addLicense";

    public static final String FUNC_GETTITLE = "getTitle";

    public static final String FUNC_SERIALIZE = "serialize";

    @Deprecated
    protected Right(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Right(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Right(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Right(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> addLicense(String _license) {
        final Function function = new Function(
                FUNC_ADDLICENSE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _license)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getTitle() {
        final Function function = new Function(FUNC_GETTITLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> serialize() {
        final Function function = new Function(FUNC_SERIALIZE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static Right load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Right(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Right load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Right(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Right load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Right(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Right load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Right(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Right> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _title, String _registrationNumber, BigInteger _issueTime, BigInteger _expireTime, String _description) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_title), 
                new org.web3j.abi.datatypes.Utf8String(_registrationNumber), 
                new org.web3j.abi.datatypes.generated.Uint64(_issueTime), 
                new org.web3j.abi.datatypes.generated.Uint64(_expireTime), 
                new org.web3j.abi.datatypes.Utf8String(_description)));
        return deployRemoteCall(Right.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Right> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _title, String _registrationNumber, BigInteger _issueTime, BigInteger _expireTime, String _description) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_title), 
                new org.web3j.abi.datatypes.Utf8String(_registrationNumber), 
                new org.web3j.abi.datatypes.generated.Uint64(_issueTime), 
                new org.web3j.abi.datatypes.generated.Uint64(_expireTime), 
                new org.web3j.abi.datatypes.Utf8String(_description)));
        return deployRemoteCall(Right.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Right> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _title, String _registrationNumber, BigInteger _issueTime, BigInteger _expireTime, String _description) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_title), 
                new org.web3j.abi.datatypes.Utf8String(_registrationNumber), 
                new org.web3j.abi.datatypes.generated.Uint64(_issueTime), 
                new org.web3j.abi.datatypes.generated.Uint64(_expireTime), 
                new org.web3j.abi.datatypes.Utf8String(_description)));
        return deployRemoteCall(Right.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Right> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _title, String _registrationNumber, BigInteger _issueTime, BigInteger _expireTime, String _description) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_title), 
                new org.web3j.abi.datatypes.Utf8String(_registrationNumber), 
                new org.web3j.abi.datatypes.generated.Uint64(_issueTime), 
                new org.web3j.abi.datatypes.generated.Uint64(_expireTime), 
                new org.web3j.abi.datatypes.Utf8String(_description)));
        return deployRemoteCall(Right.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}

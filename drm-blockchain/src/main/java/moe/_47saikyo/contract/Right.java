package moe._47saikyo.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
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
    public static final String BINARY = "608060405234801561001057600080fd5b5060405161135438038061135483398101604081905261002f916101c4565b600080546001600160a01b03191630179055600161004d8982610366565b50600361005a8882610366565b5060046100678782610366565b50600580546001600160401b0386811668010000000000000000026001600160801b03199092169088161717905560066100a18482610366565b5060086100ae8382610366565b5060096100bb8282610366565b5050600280546001600160a01b0319908116331790915560008054909116301790555061042595505050505050565b634e487b7160e01b600052604160045260246000fd5b600082601f83011261011157600080fd5b81516001600160401b038082111561012b5761012b6100ea565b604051601f8301601f19908116603f01168101908282118183101715610153576101536100ea565b816040528381526020925086602085880101111561017057600080fd5b600091505b838210156101925785820183015181830184015290820190610175565b6000602085830101528094505050505092915050565b80516001600160401b03811681146101bf57600080fd5b919050565b600080600080600080600080610100898b0312156101e157600080fd5b88516001600160401b03808211156101f857600080fd5b6102048c838d01610100565b995060208b015191508082111561021a57600080fd5b6102268c838d01610100565b985060408b015191508082111561023c57600080fd5b6102488c838d01610100565b975061025660608c016101a8565b965061026460808c016101a8565b955060a08b015191508082111561027a57600080fd5b6102868c838d01610100565b945060c08b015191508082111561029c57600080fd5b6102a88c838d01610100565b935060e08b01519150808211156102be57600080fd5b506102cb8b828c01610100565b9150509295985092959890939650565b600181811c908216806102ef57607f821691505b60208210810361030f57634e487b7160e01b600052602260045260246000fd5b50919050565b601f821115610361576000816000526020600020601f850160051c8101602086101561033e5750805b601f850160051c820191505b8181101561035d5782815560010161034a565b5050505b505050565b81516001600160401b0381111561037f5761037f6100ea565b6103938161038d84546102db565b84610315565b602080601f8311600181146103c857600084156103b05750858301515b600019600386901b1c1916600185901b17855561035d565b600085815260208120601f198616915b828110156103f7578886015182559484019460019091019084016103d8565b50858210156104155787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b610f20806104346000396000f3fe608060405234801561001057600080fd5b50600436106100625760003560e01c80638493f71f146100675780639cf834cb14610085578063a5b06b3b1461008d578063bc8018b1146100a2578063ccaf0804146100aa578063ff3c1a8f1461010c575b600080fd5b61006f610114565b60405161007c9190610883565b60405180910390f35b61006f6101a6565b6100956101b5565b60405161007c91906108b6565b61006f610216565b61010a6100b8366004610903565b600780546001810182556000919091527fa66cc928b5edb82af9bd49922954155ab7b0942694bea4ce44661d9a8736c6880180546001600160a01b0319166001600160a01b0392909216919091179055565b005b61006f61054c565b60606009805461012390610933565b80601f016020809104026020016040519081016040528092919081815260200182805461014f90610933565b801561019c5780601f106101715761010080835404028352916020019161019c565b820191906000526020600020905b81548152906001019060200180831161017f57829003601f168201915b5050505050905090565b60606004805461012390610933565b6060600780548060200260200160405190810160405280929190818152602001828054801561019c57602002820191906000526020600020905b81546001600160a01b031681526001909101906020018083116101ef575050505050905090565b6040805180820190915260018152607b60f81b6020820152600054606091908190610249906001600160a01b031661055b565b60405160200161025a92919061096d565b604051602081830303815290604052905080600160405160200161027f929190610a5c565b60408051601f1981840301815291905260025490915081906102a9906001600160a01b031661055b565b6040516020016102ba929190610aa2565b60405160208183030381529060405290508060036040516020016102df929190610af8565b6040516020818303038152906040529050806004604051602001610304929190610b2a565b60408051601f19818403018152919052600554909150819061032f9067ffffffffffffffff1661073c565b604051602001610340929190610b69565b60408051808303601f1901815291905260055490915081906103779068010000000000000000900467ffffffffffffffff1661073c565b604051602001610388929190610bc0565b60405160208183030381529060405290508060066040516020016103ad929190610c18565b60405160208183030381529060405290508060086040516020016103d2929190610c50565b60405160208183030381529060405290508060096040516020016103f7929190610c85565b6040516020818303038152906040529050806040516020016104199190610cba565b604051602081830303815290604052905060005b60075481101561052457816007828154811061044b5761044b610cea565b60009182526020822001546040805163bc8018b160e01b815290516001600160a01b039092169263bc8018b1926004808401938290030181865afa158015610497573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f191682016040526104bf9190810190610d16565b6040516020016104d0929190610dc3565b60408051601f198184030181529190526007549092506104f290600190610e08565b81101561051c578160405160200161050a9190610e21565b60405160208183030381529060405291505b60010161042d565b50806040516020016105369190610e46565b60408051601f1981840301815291905292915050565b60606001805461012390610933565b604080518082018252601081526f181899199a1a9b1b9c1cb0b131b232b360811b60208201528151602a80825260608281019094526001600160a01b0385169291600091602082018180368337019050509050600360fc1b816000815181106105c6576105c6610cea565b60200101906001600160f81b031916908160001a905350600f60fb1b816001815181106105f5576105f5610cea565b60200101906001600160f81b031916908160001a90535060005b6014811015610733578260048561062784600c610e6c565b6020811061063757610637610cea565b1a60f81b6001600160f81b031916901c60f81c60ff168151811061065d5761065d610cea565b01602001516001600160f81b03191682610678836002610e7f565b610683906002610e6c565b8151811061069357610693610cea565b60200101906001600160f81b031916908160001a90535082846106b783600c610e6c565b602081106106c7576106c7610cea565b825191901a600f169081106106de576106de610cea565b01602001516001600160f81b031916826106f9836002610e7f565b610704906003610e6c565b8151811061071457610714610cea565b60200101906001600160f81b031916908160001a90535060010161060f565b50949350505050565b6060816000036107635750506040805180820190915260018152600360fc1b602082015290565b8160005b811561078d578061077781610e96565b91506107869050600a83610eaf565b9150610767565b60008167ffffffffffffffff8111156107a8576107a8610d00565b6040519080825280601f01601f1916602001820160405280156107d2576020820181803683370190505b509050815b8515610733576107e8600182610e08565b905060006107f7600a88610eaf565b61080290600a610e7f565b61080c9088610e08565b610817906030610ed1565b905060008160f81b90508084848151811061083457610834610cea565b60200101906001600160f81b031916908160001a905350610856600a89610eaf565b975050506107d7565b60005b8381101561087a578181015183820152602001610862565b50506000910152565b60208152600082518060208401526108a281604085016020870161085f565b601f01601f19169190910160400192915050565b6020808252825182820181905260009190848201906040850190845b818110156108f75783516001600160a01b0316835292840192918401916001016108d2565b50909695505050505050565b60006020828403121561091557600080fd5b81356001600160a01b038116811461092c57600080fd5b9392505050565b600181811c9082168061094757607f821691505b60208210810361096757634e487b7160e01b600052602260045260246000fd5b50919050565b6000835161097f81846020880161085f565b671130b23239111d1160c11b90830190815283516109a481600884016020880161085f565b61088b60f21b60089290910191820152600a01949350505050565b8054600090600181811c90808316806109d957607f831692505b602080841082036109fa57634e487b7160e01b600052602260045260246000fd5b818015610a0e5760018114610a2357610a50565b60ff1986168952841515850289019650610a50565b60008881526020902060005b86811015610a485781548b820152908501908301610a2f565b505084890196505b50505050505092915050565b60008351610a6e81846020880161085f565b68113a34ba3632911d1160b91b908301908152610a8e60098201856109bf565b61088b60f21b815260020195945050505050565b60008351610ab481846020880161085f565b6b113232b83637bcb2b9111d1160a11b9083019081528351610add81600c84016020880161085f565b61088b60f21b600c9290910191820152600e01949350505050565b60008351610b0a81846020880161085f565b681137bbb732b9111d1160b91b908301908152610a8e60098201856109bf565b60008351610b3c81846020880161085f565b75113932b3b4b9ba3930ba34b7b7273ab6b132b9111d1160511b908301908152610a8e60168201856109bf565b60008351610b7b81846020880161085f565b6c1134b9b9bab2aa34b6b2911d1160991b9083019081528351610ba581600d84016020880161085f565b61088b60f21b600d9290910191820152600f01949350505050565b60008351610bd281846020880161085f565b6d1132bc3834b932aa34b6b2911d1160911b9083019081528351610bfd81600e84016020880161085f565b61088b60f21b600e9290910191820152601001949350505050565b60008351610c2a81846020880161085f565b6e113232b9b1b934b83a34b7b7111d1160891b908301908152610a8e600f8201856109bf565b60008351610c6281846020880161085f565b6b113334b632a730b6b2911d1160a11b908301908152610a8e600c8201856109bf565b60008351610c9781846020880161085f565b6b113334b632a430b9b4111d1160a11b908301908152610a8e600c8201856109bf565b60008251610ccc81846020870161085f565b6b226c6963656e736573223a5b60a01b920191825250600c01919050565b634e487b7160e01b600052603260045260246000fd5b634e487b7160e01b600052604160045260246000fd5b600060208284031215610d2857600080fd5b815167ffffffffffffffff80821115610d4057600080fd5b818401915084601f830112610d5457600080fd5b815181811115610d6657610d66610d00565b604051601f8201601f19908116603f01168101908382118183101715610d8e57610d8e610d00565b81604052828152876020848701011115610da757600080fd5b610db883602083016020880161085f565b979650505050505050565b60008351610dd581846020880161085f565b835190830190610de981836020880161085f565b01949350505050565b634e487b7160e01b600052601160045260246000fd5b81810381811115610e1b57610e1b610df2565b92915050565b60008251610e3381846020870161085f565b600b60fa1b920191825250600101919050565b60008251610e5881846020870161085f565b615d7d60f01b920191825250600201919050565b80820180821115610e1b57610e1b610df2565b8082028115828204841417610e1b57610e1b610df2565b600060018201610ea857610ea8610df2565b5060010190565b600082610ecc57634e487b7160e01b600052601260045260246000fd5b500490565b60ff8181168382160190811115610e1b57610e1b610df256fea2646970667358221220767a5e81630ec46db3e39a0c8a436b232c0bef41aa8ed0d424dc43ecee2bb94b64736f6c63430008190033";

    public static final String FUNC_ADDLICENSE = "addLicense";

    public static final String FUNC_GETFILEHASH = "getFileHash";

    public static final String FUNC_GETLICENSES = "getLicenses";

    public static final String FUNC_GETREGISTRATIONNUMBER = "getRegistrationNumber";

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

    public RemoteFunctionCall<String> getFileHash() {
        final Function function = new Function(FUNC_GETFILEHASH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<List> getLicenses() {
        final Function function = new Function(FUNC_GETLICENSES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<String> getRegistrationNumber() {
        final Function function = new Function(FUNC_GETREGISTRATIONNUMBER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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

    public static RemoteCall<Right> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _title, String _owner, String _registrationNumber, BigInteger _issueTime, BigInteger _expireTime, String _description, String _fileName, String _fileHash) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_title), 
                new org.web3j.abi.datatypes.Utf8String(_owner), 
                new org.web3j.abi.datatypes.Utf8String(_registrationNumber), 
                new org.web3j.abi.datatypes.generated.Uint64(_issueTime), 
                new org.web3j.abi.datatypes.generated.Uint64(_expireTime), 
                new org.web3j.abi.datatypes.Utf8String(_description), 
                new org.web3j.abi.datatypes.Utf8String(_fileName), 
                new org.web3j.abi.datatypes.Utf8String(_fileHash)));
        return deployRemoteCall(Right.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Right> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _title, String _owner, String _registrationNumber, BigInteger _issueTime, BigInteger _expireTime, String _description, String _fileName, String _fileHash) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_title), 
                new org.web3j.abi.datatypes.Utf8String(_owner), 
                new org.web3j.abi.datatypes.Utf8String(_registrationNumber), 
                new org.web3j.abi.datatypes.generated.Uint64(_issueTime), 
                new org.web3j.abi.datatypes.generated.Uint64(_expireTime), 
                new org.web3j.abi.datatypes.Utf8String(_description), 
                new org.web3j.abi.datatypes.Utf8String(_fileName), 
                new org.web3j.abi.datatypes.Utf8String(_fileHash)));
        return deployRemoteCall(Right.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Right> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _title, String _owner, String _registrationNumber, BigInteger _issueTime, BigInteger _expireTime, String _description, String _fileName, String _fileHash) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_title), 
                new org.web3j.abi.datatypes.Utf8String(_owner), 
                new org.web3j.abi.datatypes.Utf8String(_registrationNumber), 
                new org.web3j.abi.datatypes.generated.Uint64(_issueTime), 
                new org.web3j.abi.datatypes.generated.Uint64(_expireTime), 
                new org.web3j.abi.datatypes.Utf8String(_description), 
                new org.web3j.abi.datatypes.Utf8String(_fileName), 
                new org.web3j.abi.datatypes.Utf8String(_fileHash)));
        return deployRemoteCall(Right.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Right> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _title, String _owner, String _registrationNumber, BigInteger _issueTime, BigInteger _expireTime, String _description, String _fileName, String _fileHash) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_title), 
                new org.web3j.abi.datatypes.Utf8String(_owner), 
                new org.web3j.abi.datatypes.Utf8String(_registrationNumber), 
                new org.web3j.abi.datatypes.generated.Uint64(_issueTime), 
                new org.web3j.abi.datatypes.generated.Uint64(_expireTime), 
                new org.web3j.abi.datatypes.Utf8String(_description), 
                new org.web3j.abi.datatypes.Utf8String(_fileName), 
                new org.web3j.abi.datatypes.Utf8String(_fileHash)));
        return deployRemoteCall(Right.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
